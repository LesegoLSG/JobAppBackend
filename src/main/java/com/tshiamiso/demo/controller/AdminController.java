package com.tshiamiso.demo.controller;

import com.tshiamiso.demo.dto.CreateUserRequest;
import com.tshiamiso.demo.services.AdminService;
import com.tshiamiso.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi Adminssss");
    }

    /** POST /api/v1/user → create an account directly, e.g. add a colleague as ADMIN. Returns 201. */
    @PostMapping("/createUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) throws Throwable {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createUser(request));
    }



}
