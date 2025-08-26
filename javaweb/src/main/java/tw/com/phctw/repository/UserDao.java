package tw.com.phctw.repository;

import java.util.List;
import java.util.Optional;

import tw.com.phctw.model.dto.UserUpdateRequest;
import tw.com.phctw.model.entity.User;

public interface UserDao {
    
    boolean existsByUsername(String username);

    List<User> findAll();

    Optional<User> findByUsername(String username);

    User create(User user);

    int updatePassword(Long id, String hashed);

    User update(UserUpdateRequest userUpdateRequest);
}
