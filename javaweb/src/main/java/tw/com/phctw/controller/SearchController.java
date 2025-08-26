package tw.com.phctw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tw.com.phctw.model.entity.User;
import tw.com.phctw.service.UserService;

/**
 * SearchController
 * 負責使用者搜尋功能：
 * - 顯示搜尋頁面
 * - 處理搜尋請求
 */
@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    /**
     * 顯示搜尋頁面
     * - 新增空的 User 物件供表單綁定
     */
    @GetMapping("/search")
    public String showSearch(Model model) {
        model.addAttribute("searchForm", new User());
        return "search";
    }

    /**
     * 處理搜尋表單送出
     * - 若輸入為空，取得所有使用者
     * - 若有輸入 username，依 username 搜尋
     * - 若搜尋過程發生異常，將錯誤訊息帶回前端
     */
    @PostMapping("/search")
    public String processSearch(@ModelAttribute("searchForm") User userForm, Model model) {
        try {
            String username = (userForm.getUsername() != null) ? userForm.getUsername().trim() : "";

            List<User> userList = new ArrayList<>();

            if (username.isEmpty()) {
                // 若搜尋欄位為空，取得所有使用者
                userList = userService.getAllUsers();
            } else {
                // 根據 username 搜尋使用者
                User userByUsername = userService.getUserByUsername(username);
                if (userByUsername != null) {
                    userList.add(userByUsername);
                }
            }

            model.addAttribute("userList", userList);

        } catch (Exception e) {
            // 捕捉搜尋過程異常，封裝錯誤訊息並傳回前端
            Map<String, String> searchError = new HashMap<>();
            searchError.put("searchException", e.getMessage());
            model.addAttribute("searchError", searchError);
        }
        return "search";
    }
}
