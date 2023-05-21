package com.dmytrohuk.weborganizer.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserSsrController {
    @GetMapping("/main")
    public String showIndex(Model model) {
        return "main";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
