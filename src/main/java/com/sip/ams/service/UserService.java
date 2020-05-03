package com.sip.ams.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sip.ams.controllers.dto.UserRegistrationDto;
import com.sip.ams.entities.User;

public interface UserService extends UserDetailsService {
	User findByEmail(String email);
	User save(UserRegistrationDto registration);
}
