package tw.com.phctw.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "USERS_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "ROLE", nullable = false, length = 50)
    private String role;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "USERNAME", nullable = false, length = 50, unique = true)
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    @Column(name = "PASSWORD", nullable = false, length = 32)
    private String password;

    @Email(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")
    @NotBlank
    @Size(max = 100)
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Size(max = 50)
    @Column(name = "NICKNAME", length = 50)
    private String nickname;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
                + ", nickname=" + nickname + "]";
    }
}
