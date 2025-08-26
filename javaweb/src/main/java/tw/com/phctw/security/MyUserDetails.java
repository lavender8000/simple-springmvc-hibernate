package tw.com.phctw.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tw.com.phctw.model.entity.User;

/**
 * MyUserDetails
 * 封裝使用者資訊，實作 Spring Security 的 UserDetails
 * - 提供帳號、密碼、權限等資訊給 Security 認證與授權使用
 */
public class MyUserDetails implements UserDetails {
    private Long id;
    private String role;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private List<GrantedAuthority> authorities;

    /**
     * 從 User 物件建立 MyUserDetails
     */
    public MyUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        
        // 將 User 的角色轉成 GrantedAuthority
        this.authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    /**
     * 透過欄位建立 MyUserDetails（預設 ROLE_USER）
     */
    public MyUserDetails(Long id, String username, String password, String email, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;

        this.role = "ROLE_USER";
        // TODO: 權限信息可擴充
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /** 使用者 ID */
    public Long getId() {
        return id;
    }

    /** 使用者 Email */
    public String getEmail() {
        return email;
    }

    /** 使用者暱稱 */
    public String getNickname() {
        return nickname;
    }

    /** 使用者角色 */
    public String getRole() {
        return role;
    }

}
