package com.java.librarymanagement.model;

public class User 
{
	private int userId;
	private String name;
	private String email;
	private String password;
	private String role;
	
	private static final String USER_ROLE = "user";
	private static final String ADMIN_ROLE = "admin";
	
	//For Creating New Users
	public User(String name, String email, String password, String role)
	{
		setName(name);
		setEmail(email);
		setPassword(password);
		setRole(role);
	}
	
	//For Accessing User Records
	public User(int userId, String name, String email, String password, String role)
	{
		setUserId(userId);
		setName(name);
		setEmail(email);
		setPassword(password);
		setRole(role);
	}
	
	public int getUserId() 
	{
		return userId;
	}
	
	public void setUserId(int userId) 
	{
		this.userId = userId;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		if(name == null || name.isBlank())
			throw new IllegalArgumentException("Name must be valid!");
		
		this.name = name.strip();
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		if(email == null || email.isBlank())
			throw new IllegalArgumentException("Email must be valid!");
		this.email = email.strip();
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		if(password == null || password.isBlank())
			throw new IllegalArgumentException("Password must be valid!");
		this.password = password.strip();
	}
	
	public String getRole() 
	{
		return role;
	}
	
	public void setRole(String role) 
	{
		if(role == null || role.isBlank())
			throw new IllegalArgumentException("Role must be valid");
		
		if(!role.strip().toLowerCase().equals(USER_ROLE) && !role.strip().toLowerCase().equals(ADMIN_ROLE))
			throw new IllegalArgumentException("Role must be user or admin!");
		this.role = role.strip().toLowerCase();
	}
	
	@Override
	public String toString() 
	{
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + ("*".repeat(password.length())) + ", role="
				+ role + "]";
	}
	
	
}
