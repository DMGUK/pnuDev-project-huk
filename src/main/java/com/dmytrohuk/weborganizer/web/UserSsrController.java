package com.dmytrohuk.weborganizer.web;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserSsrController {
    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(Model model) {
        model.addAttribute("username", model);
        return "redirect:/users/main";
    }
//    @RequestMapping("/login-error.html")
//    public String loginError(Model model){
//        model.addAttribute("loginError", true);
//        return "login";
//    }
}
