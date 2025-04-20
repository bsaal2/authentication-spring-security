package com.bishal.authentication.mappers;

import com.bishal.authentication.dto.request.UserRegisterDTO;
import com.bishal.authentication.dto.response.UserDTO;
import com.bishal.authentication.models.User;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

    @BeforeMapping
    default void beforeMapping(UserRegisterDTO userRegisterDTO, @Context PasswordEncoder passwordEncoder) {
        System.out.println("before mapping " + userRegisterDTO);
        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
    }

    User toUser(UserRegisterDTO userRegisterDTO, @Context PasswordEncoder passwordEncoder);

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTO(List<User> users);
}
