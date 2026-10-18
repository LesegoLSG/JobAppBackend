package com.tshiamiso.demo.services;

import com.tshiamiso.demo.dto.CreateUserRequest;
import com.tshiamiso.demo.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
     UserDetailsService userDetailsService();
     UserDto getUserById(Long id);
     List<UserDto> getAllUsers();
     UserDto createUser(CreateUserRequest request) throws Throwable;
}
