package com.bishal.authentication.services;

import com.bishal.authentication.dto.request.UserLoginDTO;
import com.bishal.authentication.dto.request.UserRegisterDTO;
import com.bishal.authentication.dto.response.UserDTO;
import com.bishal.authentication.mappers.UserMapper;
import com.bishal.authentication.models.User;
import com.bishal.authentication.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    public UserDTO registerNewUser(UserRegisterDTO userDTO) {
        User user = userMapper.toUser(userDTO, passwordEncoder);
        User userResult = this.userRepository.save(user);
        return userMapper.toUserDTO(userResult);
    }

    public String doLogin(UserLoginDTO userLoginDTO) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
        );

        User user = this.userRepository.findByEmail(userLoginDTO.getEmail());
        if (user == null) {
            throw new Exception("User is not found");
        }

        return this.jwtService.generateToken(user);
    }

    public UserDTO getUserById(Long userId) throws Exception {
        User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new Exception("User not found for the given id");
        }

        return this.userMapper.toUserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return userMapper.toUserDTO(users);
    }
}
