package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@Controller
@RequestMapping("/")
public class HomeController {

    public HomeController() {
    }

    @GetMapping
    public String showHomeView() {
        return "main/index";
    }

}