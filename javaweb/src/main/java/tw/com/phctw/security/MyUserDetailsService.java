package tw.com.phctw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tw.com.phctw.model.entity.User;
import tw.com.phctw.service.UserService;

/**
 * MyUserDetailsService
 * Spring Security 用於根據 username 載入使用者資訊
 * - 將 User 物件轉為 MyUserDetails
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 根據 username 載入使用者
     * - 找不到使用者則拋出 UsernameNotFoundException
     * - 找到使用者則回傳 MyUserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("找不到使用者: " + username);
        }

        return new MyUserDetails(user);
    }

}
