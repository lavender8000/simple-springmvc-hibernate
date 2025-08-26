package tw.com.phctw.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.phctw.model.entity.User;
import tw.com.phctw.service.UserService;

/**
 * ForgotController
 * 處理「忘記密碼」功能：
 * - 顯示忘記密碼頁面
 * - 接收使用者帳號並寄送新密碼至信箱
 */
@Controller
public class ForgotController {

    @Autowired
    private UserService userService;

    /**
     * 顯示忘記密碼頁面
     * - 提供空的 User 物件，供表單綁定使用
     */
    @GetMapping("/forgot")
    public String showForgot(Model model) {
        model.addAttribute("forgotForm", new User());
        return "forgot";
    }

    /**
     * 處理忘記密碼表單送出
     * - 成功：寄送新密碼到信箱，並導向登入頁面
     * - 失敗：回到忘記密碼頁面，顯示錯誤訊息
     */
    @PostMapping("/forgot")
    public String processForgot(@RequestParam("username") String username, Model model) {
        try {
            // 嘗試寄送新密碼至使用者註冊信箱
            userService.resetPasswordToEmail(username);
            model.addAttribute("message", "新密碼已發送到您的註冊信箱");
        } catch (Exception e) {
            // 捕捉錯誤（例如：帳號不存在、寄信失敗等）
            Map<String, String> registerError = new HashMap<>();
            registerError.put("forgotException", e.getMessage());
            model.addAttribute("forgotError", registerError);
            return "forgot";
        }
        return "redirect:/login";
    }
}
