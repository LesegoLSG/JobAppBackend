package com.tshiamiso.demo.repository;

import com.tshiamiso.demo.dto.UserDto;
import com.tshiamiso.demo.entities.Role;
import com.tshiamiso.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);

    boolean existsByEmailIgnoreCase(String email);
}
