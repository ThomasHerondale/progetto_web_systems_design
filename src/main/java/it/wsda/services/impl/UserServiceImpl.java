package it.wsda.services.impl;

import it.wsda.dto.NewUserDTO;
import it.wsda.dto.UserDTO;
import it.wsda.entity.User;
import it.wsda.repository.UserRepository;
import it.wsda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(NewUserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(newUser);
    }

    @Override
    public UserDTO mapToUserDTO(User user) {
        return new UserDTO(user.getUsername());
    }

    @Override
    public UserDTO mapToUserDTO(NewUserDTO user) {
        return new UserDTO(user.getUsername());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findById(username).orElse(null);
        return user != null ? mapToUserDTO(user) : null;
    }
}