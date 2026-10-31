package com.tshiamiso.demo.services.impl;

import com.tshiamiso.demo.dto.CreateUserRequest;
import com.tshiamiso.demo.entities.User;
import com.tshiamiso.demo.repository.AdminRepository;
import com.tshiamiso.demo.services.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    // Look-alike characters (0/O, 1/l/I) are left out so the password is easy to read and type
    private static final String UPPER = "ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijkmnpqrstuvwxyz";
    private static final String DIGITS = "23456789";
    private static final String SYMBOLS = "!@#$%&*?";
    private static final String ALL = UPPER + LOWER + DIGITS + SYMBOLS;
    private static final int PASSWORD_LENGTH = 12;

    private final SecureRandom random = new SecureRandom();

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------- Create ----------------
    @Override
    @Transactional
    public String createUser(CreateUserRequest request) throws Throwable {
        String email = request.getEmail().trim().toLowerCase();
        if (adminRepository.existsByEmailIgnoreCase(email)) {
            throw new Throwable("EMAIL_TAKEN");
        }
        String temporaryPassword = generateTemporaryPassword();

        User user = new User();
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(temporaryPassword));
        user.setRole(request.getRole());
        adminRepository.save(user);
        return "User " + email + " created successfully. Temporary password: " + temporaryPassword;
    }

    /** 12 characters, always with at least one uppercase, lowercase, digit and symbol. */
    private String generateTemporaryPassword() {
        List<Character> chars = new ArrayList<>();
        chars.add(randomChar(UPPER));
        chars.add(randomChar(LOWER));
        chars.add(randomChar(DIGITS));
        chars.add(randomChar(SYMBOLS));
        while (chars.size() < PASSWORD_LENGTH) {
            chars.add(randomChar(ALL));
        }
        Collections.shuffle(chars, random);   // so the guaranteed characters aren't always first

        StringBuilder password = new StringBuilder();
        chars.forEach(password::append);
        return password.toString();
    }

    private char randomChar(String source) {
        return source.charAt(random.nextInt(source.length()));
    }
}
