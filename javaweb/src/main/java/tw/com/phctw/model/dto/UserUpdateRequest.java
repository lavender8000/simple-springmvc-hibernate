package tw.com.phctw.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {
    
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String role;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    
    @Email(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")
    @NotBlank
    @Size(max = 100)
    private String email;

    @Size(max = 50)
    private String nickname;

    public UserUpdateRequest(Long id, @NotBlank @Size(min = 3, max = 50) String username,
            @Email @NotBlank @Size(max = 100) String email, @Size(max = 50) String nickname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
    }

    public UserUpdateRequest() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserUpdateRequest [id=" + id + ", username=" + username + ", email=" + email + ", nickname=" + nickname
                + "]";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
