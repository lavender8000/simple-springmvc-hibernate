package tw.com.phctw.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tw.com.phctw.model.entity.User;
import tw.com.phctw.security.MyUserDetails;

/**
 * LogoutController
 * 負責處理登出頁面顯示
 * - 若未登入則導向登入頁
 * - 若已登入則顯示登出確認頁
 */
@Controller
public class LogoutController {

    /**
     * 顯示登出頁面
     * - 未登入：導向登入頁
     * - 已登入：將使用者名稱帶入 model，供前端顯示
     */
    @GetMapping("/logout")
    public String showLogout(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        // 未登入，導向登入頁
        if (userDetails == null) {
            return "redirect:/login";
        }
        
        // 將登入使用者名稱帶入表單，供畫面顯示
        User user = new User();
        user.setUsername(userDetails.getUsername());
        model.addAttribute("logoutForm", user);
        return "logout";
    }
}
