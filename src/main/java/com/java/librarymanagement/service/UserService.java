package com.java.librarymanagement.service;

import java.util.List;

import com.java.librarymanagement.model.User;

public interface UserService 
{
	boolean addUser(User user);
	
	User getUserById(int id);
	
	User getUserByEmail(String email);
	
	List<User> getAllUsers();
	
	boolean updateUser(User user);
	
	boolean deleteUser(int id);
	
	boolean login(String email, String password);
}
