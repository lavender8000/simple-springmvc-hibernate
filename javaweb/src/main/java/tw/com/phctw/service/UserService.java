package tw.com.phctw.service;

import java.util.List;

import tw.com.phctw.model.dto.UserUpdateRequest;
import tw.com.phctw.model.entity.User;

public interface UserService {

    boolean isUsernameExists(String username);

    User register(User user);

    void resetPasswordToEmail(String username);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User updateUser(UserUpdateRequest userUpdateRequest);
}
