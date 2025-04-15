package com.bishal.authentication.services;

import com.bishal.authentication.dto.request.UserLoginDTO;
import com.bishal.authentication.dto.request.UserRegisterDTO;
import com.bishal.authentication.models.User;
import com.bishal.authentication.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserRegisterDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPassword);

        return this.userRepository.save(user);
    }

    public User doLogin(UserLoginDTO userLoginDTO) throws Exception {
        User user = this.userRepository.findByEmail(userLoginDTO.getEmail());
        if (user == null) {
            throw new Exception("User is not found");
        }

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new Exception("Incorrect password");
        }

        return user;
    }
}
