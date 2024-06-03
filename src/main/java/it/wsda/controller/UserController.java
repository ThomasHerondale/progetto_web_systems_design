package it.wsda.controller;

import it.wsda.dto.NewUserDTO;
import it.wsda.dto.UserDTO;
import it.wsda.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new NewUserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute NewUserDTO user, Model model) {
        model.addAttribute("user", user);
        return "index_admin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new NewUserDTO());
        return "createUser";
    }

    @PostMapping("/create")
    public String createUser(NewUserDTO userDTO, Model model) {
        try {
            userService.createUser(userDTO);
            model.addAttribute("message", "User created successfully!");
            model.addAttribute("user", new NewUserDTO());
        } catch (Exception e) {
            model.addAttribute("message", "Error creating user: " + e.getMessage());
            model.addAttribute("user", userDTO);
        }
        return "createUser";
    }
}