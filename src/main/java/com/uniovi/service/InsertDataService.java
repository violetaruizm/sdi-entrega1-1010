package com.uniovi.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;

@Service
public class InsertDataService {
	
	
	@Autowired
	private UsersService usersService;
	
	@PostConstruct
	public void init() {
		User user = new User();
		user.setEmail("admin@email.com");
		user.setPassword("admin");
		user.setSurname("admin");
		user.setRole(Role.ROLE_ADMIN);
		user.setValid(true);
		usersService.addUser(user);
		
		
		User user1 = new User();
		user1.setEmail("abc@gmail.com");
		user1.setPassword("132245");
		user1.setRole(Role.ROLE_STANDARD);
		user1.setSurname("abc");
		user1.setValid(true);
		usersService.addUser(user1);
		
		User user2 = new User();
		user2.setEmail("def@gmail.com");
		user2.setPassword("132245");
		user2.setRole(Role.ROLE_STANDARD);
		user2.setSurname("def");
		user2.setValid(true);
		usersService.addUser(user2);
	}
	
	

}
