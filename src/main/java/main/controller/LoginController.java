package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Adam Doliński
 * 09.04.2020
 */
@Controller
public class LoginController {
    @GetMapping("/login-form")
    public String loginPage() {
        return "login-form";
    }
}
