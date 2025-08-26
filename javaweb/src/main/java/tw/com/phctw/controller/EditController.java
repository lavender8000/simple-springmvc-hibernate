package tw.com.phctw.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import tw.com.phctw.model.dto.UserUpdateRequest;
import tw.com.phctw.security.MyUserDetails;
import tw.com.phctw.service.UserService;

/**
 * EditController 負責處理會員資料的編輯功能
 * - 顯示編輯頁面
 * - 處理使用者送出的更新請求
 */
@Controller
public class EditController {

    @Autowired
    private UserService userService;

    /**
     * 顯示編輯頁面
     * - 若未登入，導向登入頁面
     * - 若已登入，將使用者資料帶入表單
     */
    @GetMapping("/edit")
    public String showEdit(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        // 未登入時導向登入頁
        if (userDetails == null) {
            return "redirect:/login";
        }

        // 將登入使用者的資料帶入 editForm，供畫面綁定
        model.addAttribute("editForm", userDetails);
        return "edit";
    }

    /**
     * 處理編輯表單送出
     * - 表單驗證錯誤：回傳原頁面並顯示錯誤訊息
     * - 更新成功：導向個人資料頁
     * - 更新失敗（例外狀況）：回傳錯誤訊息並停留在原頁面
     */
    @PostMapping("/edit")
    public String processEdit(
            @ModelAttribute("editForm") @Valid UserUpdateRequest editForm,
            BindingResult result,
            HttpSession session,
            Model model) {

        // 表單驗證錯誤
        if (result.hasErrors()) {
            // 收集所有欄位的錯誤訊息，傳回前端顯示
            Map<String, String> editError = new LinkedHashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                editError.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("editError", editError);

            return "edit";
        } else {
            try {
                // 嘗試更新使用者資料（可能丟出例外，如 DB 錯誤）
                userService.updateUser(editForm);

                return "redirect:/profile";
            } catch (Exception e) {
                // 捕捉更新過程的異常，例如資料庫更新失敗
                Map<String, String> editError = new HashMap<>();
                editError.put("editException", e.getMessage());
                model.addAttribute("editError", editError);

                return "edit";
            }
        }
    }
}
