package com.tshiamiso.demo;

import com.tshiamiso.demo.dto.SignUpRequest;
import com.tshiamiso.demo.entities.Role;
import com.tshiamiso.demo.entities.User;
import com.tshiamiso.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TshiamisorecruitmentApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TshiamisorecruitmentApplication.class, args);
	}

	public void run(String... args){

		System.out.println("Server started sucessfully");
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(adminAccount == null){
			User user = new User();
			user.setFirstName("Lesego");
			user.setLastName("Mhlongo");
			user.setRole(Role.ADMIN);
			user.setEmail("lesegomhlongo78@gmail.com");
			user.setPassword(new BCryptPasswordEncoder().encode("lesego"));
			userRepository.save(user);
		}
	}

}
