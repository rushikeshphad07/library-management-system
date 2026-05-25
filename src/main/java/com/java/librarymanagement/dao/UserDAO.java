package com.java.librarymanagement.dao;

import java.util.List;

import com.java.librarymanagement.model.User;

public interface UserDAO 
{
	boolean addUser(User user);
	
	User getUserByEmail(String email);
	
	User getUserById(int id);
	
	List<User> getAllUsers();
	
	boolean updateUser(User user);
	
	boolean deleteUser(int id);
	
	boolean updateUserRole(int id, String role);
}
