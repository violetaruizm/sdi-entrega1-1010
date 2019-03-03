package com.uniovi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class SecurityService {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
	
	public String findLoggedInEMail() {
		
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		if(userDetails instanceof UserDetails) {
			return ((UserDetails)userDetails).getUsername();
		}
		
		return null;
	}
	
	public void autoLogin(String email,String password) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		
		UsernamePasswordAuthenticationToken aToken = new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
		
		authenticationManager.authenticate(aToken);
		
		if(aToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(aToken);
			logger.debug(String.format("Auto login %s succesfully!", email));
		}
	}
	
	
		

}
