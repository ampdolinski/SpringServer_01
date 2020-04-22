package main.controller;

import main.common.SystemMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Adam Doliński
 * 22.04.2020
 */

@Controller
public class ServerPanelController {

    @GetMapping("/server")
    public String showServerPanelView() {
        return "server";
    }

    @PostMapping("/server")
    public String showServer(Model model) {

        String serverName = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("message",
                new SystemMessage("Success", "You just logged-in as SERVER"));
        return "message";
    }
}