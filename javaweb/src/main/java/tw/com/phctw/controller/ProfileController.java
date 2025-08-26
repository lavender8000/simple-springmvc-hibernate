package tw.com.phctw.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tw.com.phctw.security.MyUserDetails;

/**
 * ProfileController
 * 顯示登入使用者的個人資料頁面
 */
@Controller
public class ProfileController {

    /**
     * 顯示個人資料頁面
     * - 將登入使用者資訊加入 model，供前端顯示
     */
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails);
        return "profile";
    }

}
