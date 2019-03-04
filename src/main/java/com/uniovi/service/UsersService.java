package com.uniovi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;

@Service
public class UsersService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/*@PostConstruct
	public void init() {
	}*/

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User getUser(String email) {
		return userRepository.findByEmail(email);
	}
	
	

	public void addUser(User user) {
		if(user.getPassword()!=null) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));}
		userRepository.save(user);
	}
	
	

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}


}
