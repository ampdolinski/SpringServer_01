package main.controller;

import main.common.SystemMessage;
import main.exception.PasswordsDoNotMatchException;
import main.exception.UserNotFoundException;
import main.exception.WrongOldPasswordException;
import main.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/userPanel")
public class UserPanelController {

    private UserService userService;

    public UserPanelController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/accountSettings")
    public String showUserSettings(Model model) throws UserNotFoundException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.getUserByUserName(userName));
        model.addAttribute("username", userName);
        return "user-form";
    }

//    @RequestMapping("/bookings")
//    public String showUserBookings(@ModelAttribute("user") User user, Model model) throws UserNotFoundException {
//        bookingsViewModelAttributes(model);
//        return "bookings";
//    }
//
//    @PostMapping("/booking/delete/{id}")
//    public String deleteBooking(@PathVariable Long id, Model model) throws BookingNotFoundException, UserNotFoundException {
//        bookingService.deleteBooking(id);
//        bookingsViewModelAttributes(model);
//        return "redirect:/userPanel/bookings";
//    }

    private void otherClientsViewModelAttributes(Model model) throws UserNotFoundException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.getUserByUserName(userName));
        model.addAttribute("username", userName);
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword, Model model) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            userService.changePassword(userName, oldPassword, newPassword, confirmPassword);
        } catch (UserNotFoundException | PasswordsDoNotMatchException | WrongOldPasswordException e) {
            e.getMessage();
            e.printStackTrace();
            model.addAttribute("url", "/userPanel/changePassword");
            model.addAttribute("message",
                    new SystemMessage("Warning", e.getMessage()));
            return "message";
        }

        model.addAttribute("url", "/userPanel/changePassword");
        model.addAttribute("message",
                new SystemMessage("Success", "Password has been changed"));
        return "message";
    }

    @GetMapping("/changePassword")
    public String returnSomething() {
        return "change-password";
    }

}
