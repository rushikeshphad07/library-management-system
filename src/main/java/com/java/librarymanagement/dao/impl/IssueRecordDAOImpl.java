package com.java.librarymanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.java.librarymanagement.dao.IssueRecordDAO;
import com.java.librarymanagement.model.IssueRecord;
import com.java.librarymanagement.util.DBConnection;

public class IssueRecordDAOImpl implements IssueRecordDAO 
{
	
	@Override
	public boolean issueBook(IssueRecord record) 
	{
		String checkBook = "SELECT available_quantity FROM books WHERE book_id=?";
		String insertQuery = "INSERT INTO issue_records(user_id, book_id, issue_date, due_date, status) VALUES(?, ?, ?, ?, ?)";
		String updateBookQuery = "UPDATE books SET available_quantity = available_quantity-1 WHERE book_id=? AND available_quantity>0";
		
		try
		(
				Connection conn = DBConnection.getConnection();
		)
		{
				conn.setAutoCommit(false);
				
				//1. Check Book Availability
				try
				(
						PreparedStatement checkPstmt = conn.prepareStatement(checkBook);
				)
				{
					checkPstmt.setInt(1, record.getBookId());
					
					ResultSet rs = checkPstmt.executeQuery();
					
					if(rs.next())
					{
						int availableQuantity = rs.getInt("available_quantity");
						
						if(availableQuantity <= 0)
						{
							conn.rollback();
							return false;
						}
					}
					else
					{
						conn.rollback();
						return false;
					}
				}
				
				//2. Insert Issue Record
				try
				(
						PreparedStatement insertPstmt = conn.prepareStatement(insertQuery);
				)
				{
					insertPstmt.setInt(1, record.getUserId());
					insertPstmt.setInt(2, record.getBookId());
					insertPstmt.setDate(3, java.sql.Date.valueOf(record.getIssueDate()));
					insertPstmt.setDate(4, java.sql.Date.valueOf(record.getDueDate()));
					insertPstmt.setString(5, record.getStatus());
					
					int rowsInserted = insertPstmt.executeUpdate();
					
					if(rowsInserted == 0)
					{
						conn.rollback();
						return false;
					}
				}
				//3. Reduce Available Quantity
				try
				(
						PreparedStatement updatePstmt = conn.prepareStatement(updateBookQuery);
				)
				{
					updatePstmt.setInt(1, record.getBookId());
					
					int rowsAffected = updatePstmt.executeUpdate();
					
					//No Stock
					if(rowsAffected == 0)
					{
						conn.rollback();
						return false;
					}
				}
				
				conn.commit();
				return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean returnBook(int issueId) 
	{
		String getBookId = "SELECT book_id FROM issue_records WHERE issue_id=? and STATUS='issued'";
		String returnQuery = "UPDATE issue_records SET return_date=?, status=? WHERE issue_id=?";
		String updateBookQuery = "UPDATE books SET available_quantity=available_quantity+1 WHERE book_id=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
		)
		{
			conn.setAutoCommit(false);
			int bookId = 0;
			
			//1. Access BookId
			try
			(
					PreparedStatement getPstmt = conn.prepareStatement(getBookId);
			)
			{
				getPstmt.setInt(1, issueId);
				
				ResultSet rs = getPstmt.executeQuery();
				
				if(rs.next())
				{
					bookId = rs.getInt("book_id");
				}
				else
				{
					conn.rollback();
					return false;
				}
			}
			
			//2. Update Issue Record
			try
			(
					PreparedStatement returnPstmt = conn.prepareStatement(returnQuery);
			)
			{
				returnPstmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
				returnPstmt.setString(2, "returned");
				returnPstmt.setInt(3, issueId);
				
				int rowsAffected = returnPstmt.executeUpdate();
				
				if(rowsAffected == 0)
				{
					conn.rollback();
					return false;
				}
			}
			
			//3. Increase Available Quantity
			try
			(
					PreparedStatement updatePstmt = conn.prepareStatement(updateBookQuery);
			)
			{
				updatePstmt.setInt(1, bookId);
				
				int rowsAffected = updatePstmt.executeUpdate();
				
				if(rowsAffected == 0)
				{
					conn.rollback();
					return false;
				}
			}
			
			conn.commit();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public IssueRecord getIssueById(int id) 
	{
		
		String query = "SELECT * FROM issue_records WHERE id=?";
		
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
				IssueRecord record = new IssueRecord
				(
						rs.getInt("id"),
						rs.getInt("user_id"),
						rs.getInt("book_id"),
						rs.getDate("issue_date").toLocalDate(),
						rs.getDate("due_date").toLocalDate(),
						rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
						rs.getString("status")
				);
				
				return record;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<IssueRecord> getAllIssuedBooks() 
	{
		String query = "SELECT * FROM issue_records WHERE STATUS='issued'";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			ResultSet rs = pstmt.executeQuery();
			List<IssueRecord> issueList = new ArrayList<IssueRecord>();
			
			while(rs.next())
			{
				IssueRecord issueRecord = new IssueRecord
				(
						rs.getInt("Id"),
						rs.getInt("user_id"),
						rs.getInt("book_id"),
						rs.getDate("issue_date").toLocalDate(),
						rs.getDate("due_date").toLocalDate(),
						rs.getString("status")
				);
				
				issueList.add(issueRecord);
			}
			
			return issueList;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}

	@Override
	public List<IssueRecord> getIssuesByUserId(int userId) 
	{
		String query = "SELECT * FROM issue_records WHERE user_id=? AND status='issued'";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setInt(1, userId);
			
			List<IssueRecord> issueList = new ArrayList<IssueRecord>();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				IssueRecord issueRecord = new IssueRecord
				(
						rs.getInt("Id"),
						rs.getInt("user_id"),
						rs.getInt("book_id"),
						rs.getDate("issue_date").toLocalDate(),
						rs.getDate("due_date").toLocalDate(),
						rs.getString("status")
				);
				
				issueList.add(issueRecord);
			}
			
			return issueList;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<IssueRecord> getOverdueBooks() 
	{
		String query = "SELECT * FROM issue_records WHERE status='issued' AND due_date<CURDATE()";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			List<IssueRecord> issueList = new ArrayList<IssueRecord>();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				IssueRecord issueRecord = new IssueRecord
				(
						rs.getInt("Id"),
						rs.getInt("user_id"),
						rs.getInt("book_id"),
						rs.getDate("issue_date").toLocalDate(),
						rs.getDate("due_date").toLocalDate(),
						rs.getString("status")
				);
				
				issueList.add(issueRecord);
			}
			
			return issueList;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}

}
