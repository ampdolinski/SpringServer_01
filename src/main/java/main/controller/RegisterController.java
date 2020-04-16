package main.controller;

import main.entity.Role;
import main.entity.dto.UserDTO;
import main.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@AllArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;

    @GetMapping
    public String getRegistrations(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register-form";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid UserDTO userDto, BindingResult bindResult) {
        if (userService.getOptionalUserByUserName(userDto.getUserLogin()).isPresent()) {
            bindResult.rejectValue("username", null, "Account with this user name already exist.");
        }
        if (bindResult.hasErrors())
            return "register-form";
        else {
            userDto.setRole(Role.USER);
            userDto.setCreatedAt(LocalDateTime.now());
            userService.saveUser(userDto);
            return "redirect:/login-form";
        }
    }
}