package com.example.ssurvey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	User findByUserEmail(String userEmail);	
	
//	String fundByUserPassword(String userPassword);
}
