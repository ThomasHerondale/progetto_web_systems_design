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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(NewUserDTO userDTO)  {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
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