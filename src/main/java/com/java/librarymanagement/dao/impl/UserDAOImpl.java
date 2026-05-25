package com.java.librarymanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.librarymanagement.dao.UserDAO;
import com.java.librarymanagement.model.User;
import com.java.librarymanagement.util.DBConnection;

public class UserDAOImpl implements UserDAO
{
	
	private static final String USER_ROLE = "user";
	private static final String ADMIN_ROLE = "admin";

	@Override
	public boolean addUser(User user) 
	{
		String query = "INSERT INTO users(name, email, password, role) VALUES(?, ?, ?, ?)";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getRole());
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User getUserByEmail(String email) 
	{
		String query = "SELECT * FROM users WHERE email=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			 pstmt.setString(1, email);
			 ResultSet rs = pstmt.executeQuery();
			 
			 if(rs.next())
			 {
				 User user = new User
				 (
						 rs.getInt("id"),
						 rs.getString("name"),
						 rs.getString("email"),
						 rs.getString("password"),
						 rs.getString("role")
				 );
				 
				 return user;
			 }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User getUserById(int id) 
	{
		String query = "SELECT * FROM users WHERE id=?";
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			 pstmt.setInt(1, id);
			 ResultSet rs = pstmt.executeQuery();
			 
			 if(rs.next())
			 {
				 User user = new User
				 (
						 rs.getInt("id"),
						 rs.getString("name"),
						 rs.getString("email"),
						 rs.getString("password"),
						 rs.getString("role")
				 );
				 
				 return user;
			 }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() 
	{
		String query = "SELECT * FROM users";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			List<User> userList = new ArrayList<User>();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				 User user = new User
				 (
						 rs.getInt("id"),
						 rs.getString("name"),
						 rs.getString("email"),
						 rs.getString("password"),
						 rs.getString("role")
				 );
				 
				 userList.add(user);
			}
			
			return userList;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}

	@Override
	public boolean updateUser(User user) 
	{
		String query = "UPDATE users SET name=?, email=?, password=? WHERE id=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setInt(4, user.getUserId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteUser(int id) 
	{
		String query = "DELETE FROM users WHERE id=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setInt(1, id);
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean updateUserRole(int id, String role)
	{
		String query = "UPDATE users SET role=? WHERE id=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			String normalizedRole = role.strip().toLowerCase();
			if(normalizedRole.equals(USER_ROLE) || normalizedRole.equals(ADMIN_ROLE))
			{
				pstmt.setString(1, role);
				pstmt.setInt(2, id);
				
				int rowsAffected = pstmt.executeUpdate();
				
				return rowsAffected>0;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

}
