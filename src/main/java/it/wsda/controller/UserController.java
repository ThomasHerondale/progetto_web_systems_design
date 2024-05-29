package it.wsda.controller;

import it.wsda.dto.NewUserDTO;
import it.wsda.dto.UserDTO;
import it.wsda.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin/create-user")
    public String createUserForm() {
        return "/users/admin/create-user";
    }

    @PostMapping("/admin/create-user")
    @ResponseBody
    public UserDTO createUser(@Valid @RequestBody NewUserDTO userDTO, HttpServletResponse response) {
        // Controlla se l'utente esiste già
        if (userService.getUserByUsername(userDTO.getUsername()) != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }

        userService.createUser(userDTO);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return userService.mapToUserDTO(userDTO);
    }
}