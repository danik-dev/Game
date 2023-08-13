package com.example.game.controllers;

import com.example.game.entities.User;
import com.example.game.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid @ModelAttribute User user,
                           BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "sign-up";
        }
        userService.saveUser(user);
        return "home";
    }
}
