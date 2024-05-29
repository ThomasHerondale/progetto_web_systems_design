package it.wsda.services;

import it.wsda.entity.User;

import it.wsda.dto.NewUserDTO;
import it.wsda.dto.UserDTO;


public interface UserService {
    void createUser(NewUserDTO user);
    UserDTO mapToUserDTO(User user);
    UserDTO mapToUserDTO(NewUserDTO user);
    UserDTO getUserByUsername(String username);
}