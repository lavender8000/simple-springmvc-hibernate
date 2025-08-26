package tw.com.phctw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * LoginController
 * 負責處理登入頁面的顯示
 * - 由 Spring Security 負責實際的登入驗證流程
 */
@Controller
public class LoginController {

    /**
     * 顯示登入頁面
     * - 若使用者已登入，直接導向首頁
     * - 否則返回 login.jsp/html 頁面
     */
    @GetMapping("/login")
    public String showLogin(Authentication authentication) {
        // 已登入的使用者導向首頁
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

    // @PostMapping("/login")
    // 登入 POST 請求由 Spring Security 接管
    // (在 SecurityConfig 中透過 loginPage("/login") 進行配置)
}
