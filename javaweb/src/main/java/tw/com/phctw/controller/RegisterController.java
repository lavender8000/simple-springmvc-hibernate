package tw.com.phctw.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import tw.com.phctw.model.entity.User;
import tw.com.phctw.service.UserService;

/**
 * RegisterController
 * 負責處理使用者註冊功能：
 * - 顯示註冊頁面
 * - 接收使用者表單並進行驗證與註冊
 * - AJAX 驗證使用者名稱是否已存在
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 顯示註冊頁面
     * - 若使用者已登入，導向個人頁
     * - 否則返回註冊頁
     */
    @GetMapping("/register")
    public String showRegister(Authentication authentication, Model model) {
        // 已登入則導向個人資料頁
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/profile";
        }

        // 空的 User 物件，供表單綁定使用
        model.addAttribute("userForm", new User());
        return "register";
    }

    /**
     * 處理註冊表單送出
     * - 表單驗證錯誤：回到註冊頁並顯示錯誤訊息
     * - 註冊成功：導向首頁，並附加 query 參數提示
     * - 註冊失敗（例外）：回到註冊頁並顯示錯誤訊息
     */
    @PostMapping("/register")
    public String processRegister(
            @ModelAttribute("userForm") @Valid User userForm,
            BindingResult result,
            HttpSession session,
            Model model) {

        // 表單驗證錯誤
        if(result.hasErrors()) {
            // 收集所有欄位的錯誤訊息，傳回前端顯示
            Map<String, String> registerError = new LinkedHashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                registerError.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("registerError", registerError);

            return "register";
        } else {
            try {
                // 嘗試註冊使用者
                userService.register(userForm);

                return "redirect:/?create=user";
            } catch (Exception e) {
                // 捕捉註冊失敗異常（例如：帳號已存在、資料庫錯誤）
                Map<String, String> registerError = new HashMap<>();
                registerError.put("registerException", e.getMessage());
                model.addAttribute("registerError", registerError);

                return "register";
            }
        }
    }

    /**
     * AJAX 驗證使用者名稱是否已存在
     * @param username 要檢查的使用者名稱
     * @return JSON 格式 { "exists": true/false }
     */
    @PostMapping("/checkUsername")
    @ResponseBody
    public Map<String, Boolean> checkUsername(@RequestParam("username") String username) {
        boolean exists = userService.isUsernameExists(username);
        return Map.of("exists", exists);
    }

}
