package tw.com.phctw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.TransactionRequiredException;
import tw.com.phctw.model.dto.UserUpdateRequest;
import tw.com.phctw.model.entity.User;
import tw.com.phctw.repository.UserDao;
import tw.com.phctw.security.MyUserDetails;
import tw.com.phctw.service.UserService;

/**
 * UserServiceImpl
 * UserService 實作
 * - 提供使用者註冊、重置密碼、查詢、更新功能
 * - 支援更新 SecurityContext 以同步使用者資料
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /** 檢查 username 是否存在 */
    @Override
    public boolean isUsernameExists(String username) {
        return userDao.existsByUsername(username);
    }

    /**
     * 註冊新使用者
     * - 檢查帳號是否已存在
     * - 密碼加密後存入資料庫
     */
    @Transactional
    @Override
    public User register(User user) {

        if (userDao.existsByUsername(user.getUsername())) {
            throw new RuntimeException("帳號已存在");
        }

        String encodedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPwd);

        try {
            return userDao.create(user);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("User 參數錯誤", e);
        } catch (TransactionRequiredException e) {
            throw new RuntimeException("需要 transaction", e);
        } catch (PersistenceException e) {
            // Hibernate ConstraintViolationException 會被封裝成這個
            throw new RuntimeException("資料寫入失敗，可能有重複或約束違反", e);
        }
    }

    /**
     * 重置使用者密碼並發送到 Email
     * - 生成隨機新密碼
     * - 更新資料庫密碼
     * - 寄送新密碼到使用者 Email
     */
    @Transactional
    @Override
    public void resetPasswordToEmail(String username) {
        userDao.findByUsername(username)
            .ifPresentOrElse(user -> {
                // 生成 8 位隨機新密碼
                String newPass = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 8);
                String encodedPwd = passwordEncoder.encode(newPass);

                try {
                    userDao.updatePassword(user.getId(), encodedPwd);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("User 參數錯誤", e);
                } catch (TransactionRequiredException e) {
                    throw new RuntimeException("需要 transaction", e);
                } catch (PersistenceException e) {
                    throw new RuntimeException("資料寫入失敗，可能有重複或約束違反", e);
                }

                // 發送新密碼到使用者 Email
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(user.getEmail());
                msg.setSubject("新密碼通知");
                msg.setText("您的新密碼是：" + newPass);
                javaMailSender.send(msg); 
            }, () -> {
                throw new RuntimeException("用戶不存在");
            });
    }

    /** 取得所有使用者 */
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /** 根據 username 取得使用者 */
    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("用戶不存在"));
    }

    /**
     * 更新使用者資料
     * - 更新資料庫資料
     * - 更新 SecurityContext 中的 UserDetails，確保已登入的資訊同步
     */
    @Transactional
    @Override
    public User updateUser(UserUpdateRequest userUpdateRequest) {
        User user;
        try {
            user = userDao.update(userUpdateRequest);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("User 參數錯誤", e);
        } catch (TransactionRequiredException e) {
            throw new RuntimeException("需要 transaction", e);
        } catch (PersistenceException e) {
            throw new RuntimeException("資料寫入失敗，可能有重複或約束違反", e);
        }

        // 建立新的 UserDetails (已更新)
        MyUserDetails updatedUserDetails = new MyUserDetails(user);

        // 更新 Security Context 中的 UserDetails 資料
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication currentAuth = securityContext.getAuthentication();
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
            updatedUserDetails,
            currentAuth.getCredentials(),
            updatedUserDetails.getAuthorities()
        );
        securityContext.setAuthentication(newAuth);

        return user;
    }
}
