package com.java.librarymanagement.service.impl;

import java.util.List;

import com.java.librarymanagement.dao.UserDAO;
import com.java.librarymanagement.exception.InvalidCredentialsException;
import com.java.librarymanagement.exception.UserAlreadyExistsException;
import com.java.librarymanagement.exception.UserNotFoundException;
import com.java.librarymanagement.model.User;
import com.java.librarymanagement.service.UserService;

public class UserServiceImpl implements UserService 
{
	
	private UserDAO userDAO;

	public UserServiceImpl(UserDAO userDAO) 
	{
		
		if(userDAO == null)
			throw new IllegalArgumentException("DAO cannot be null!");
		
		this.userDAO = userDAO;
	}

	@Override
	public boolean addUser(User user)
	{
		
		if(user == null)
			throw new IllegalArgumentException("User cannot be null!");
		
		User existingUser = userDAO.getUserByEmail(user.getEmail());
		
		if(existingUser != null)
			throw new UserAlreadyExistsException("User already exists!");
		
		return userDAO.addUser(user);
	}

	@Override
	public User getUserById(int id)
	{
		
		if(id <= 0)
			throw new IllegalArgumentException("Invalid id!");
		
		return userDAO.getUserById(id);
	}

	@Override
	public User getUserByEmail(String email)
	{
		
		if(email == null || email.strip().isBlank())
			throw new IllegalArgumentException("Invalid email!");
		
		return userDAO.getUserByEmail(email);
	}

	@Override
	public List<User> getAllUsers()
	{
		return userDAO.getAllUsers();
	}

	@Override
	public boolean updateUser(User user)
	{
		if(user == null)
			throw new IllegalArgumentException("User cannot be null!");
		
		if(userDAO.getUserByEmail(user.getEmail()) == null) 
			throw new UserNotFoundException("User not found!");
		
		return userDAO.updateUser(user);
	}

	@Override
	public boolean deleteUser(int id) 
	{
		
		if(id <= 0)
			throw new IllegalArgumentException("Invalid id!");
		
		return userDAO.deleteUser(id);
	}

	@Override
	public boolean login(String email, String password) 
	{
		
	    if(email == null || email.strip().isBlank())
	        throw new IllegalArgumentException("Email cannot be empty");

	    if(password == null || password.strip().isBlank())
	        throw new IllegalArgumentException("Password cannot be empty");

	    User user = userDAO.getUserByEmail(email);
	    
	    if(user == null)
	    	throw new InvalidCredentialsException("Invalid email or password!");
	    
	    if(!user.getPassword().equals(password))
	    	throw new InvalidCredentialsException("Invalid email or password!");
	    
		return true;
	}
	
	@Override
	public boolean updateUserRole(int id, String role)
	{
		return userDAO.updateUserRole(id, role);
	}

}
