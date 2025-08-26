package tw.com.phctw.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import tw.com.phctw.model.dto.UserUpdateRequest;
import tw.com.phctw.model.entity.User;
import tw.com.phctw.repository.UserDao;

/**
 * UserDaoImpl
 * 使用 JPA EntityManager 實作 UserDao
 * - 提供 User 相關 CRUD 與查詢操作
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 查詢所有使用者
     */
    @Override
    public List<User> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * 依 username 查詢單一使用者
     */
    @Override
    public Optional<User> findByUsername(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);

        query.select(root)
                .where(cb.equal(root.get("username"), username));

        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .findFirst();
    }

    /**
     * 檢查 username 是否存在
     */
    @Override
    public boolean existsByUsername(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
        Root<User> root = query.from(User.class);

        query.select(cb.literal(1))
                .where(cb.equal(root.get("username"), username));

        return !entityManager.createQuery(query)
                .getResultList()
                .isEmpty();
    }

    /**
     * 建立使用者
     */
    @Override
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    /**
     * 更新使用者密碼
     */
    @Override
    public int updatePassword(Long id, String hashed) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
        Root<User> root = update.from(User.class);

        update.set(root.get("password"), hashed)
            .where(cb.equal(root.get("id"), id));

        return entityManager.createQuery(update).executeUpdate();
    }

    /**
     * 更新使用者資訊
     * - 依據 UserUpdateRequest 更新 role / email / nickname
     * - 若 nickname 為空則設定為 null
     * - 若使用者不存在，丟出 IllegalArgumentException
     */
    @Override
    public User update(UserUpdateRequest userUpdateRequest) {
        User existingUser = entityManager.find(User.class, userUpdateRequest.getId());
        
        if (existingUser == null) {
            throw new IllegalArgumentException("使用者不存在");
        }
        
        if (userUpdateRequest.getRole() != null) {
            existingUser.setRole(userUpdateRequest.getRole());
        }
        if (userUpdateRequest.getEmail() != null) {
            existingUser.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getNickname() == null || userUpdateRequest.getNickname().trim().isEmpty()) {
            existingUser.setNickname(null);
        } else {
            existingUser.setNickname(userUpdateRequest.getNickname());
        }

        return existingUser;
    }

}
