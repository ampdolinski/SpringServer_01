package main.controller;

import main.entity.Role;
import main.entity.User;
import main.entity.dto.UserDTO;
import main.exception.UserNotFoundException;
import main.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/addUser")
    public String addUserView(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserDTO userDTO, Model model) throws UserNotFoundException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName);
        userService.saveUser(userDTO);
        if (user.getRole().equals(Role.USER)) {
            otherClientsViewModelAttributes(model);
            return "redirect:/userPanel/accountSettings";
        } else {
            return "redirect:/users";
        }
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserView(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.getUserById(id));
        return "user-form";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    private void otherClientsViewModelAttributes(Model model) throws UserNotFoundException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.getUserByUserName(userName));
    }
}
