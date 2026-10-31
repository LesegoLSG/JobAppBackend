package com.tshiamiso.demo.services;

import com.tshiamiso.demo.dto.CreateUserRequest;

public interface AdminService {

    String createUser(CreateUserRequest request) throws Throwable;
}
