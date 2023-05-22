package com.dmytrohuk.weborganizer.web;

import com.dmytrohuk.weborganizer.security.AuthUser;
import com.dmytrohuk.weborganizer.users.UserLoginDTO;
import com.dmytrohuk.weborganizer.users.UserRole;
import com.dmytrohuk.weborganizer.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class UserSsrController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("/main")
    public String showIndex(Model model, @AuthenticationPrincipal AuthUser authUser) {
        model.addAttribute("username", authUser.getUsername());
        return "main";
    }

    @RequestMapping("/login")
    public String login(UserLoginDTO userLoginDTO, HttpServletRequest request) {
        String token = userService.userLogin(userLoginDTO).getAccessToken();
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        return "login";
    }
}
