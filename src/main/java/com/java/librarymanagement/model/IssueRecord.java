package com.java.librarymanagement.model;

import java.time.LocalDate;

public class IssueRecord 
{
	private int issueId; //AUTO_INCREMENT
	private int userId;
	private int bookId;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private String status;
	
	private static final String ISSUED_STATUS = "issued";
	private static final String RETURNED_STATUS = "returned";
	private static final String OVERDUE_STATUS = "overdue";
	
	//For Creating New Issue Record
	public IssueRecord(int userId, int bookId, LocalDate issueDate, LocalDate dueDate)
	{
		setUserId(userId);
		setBookId(bookId);
		setIssueDate(issueDate);
		setDueDate(dueDate);
		this.status = ISSUED_STATUS;
	}
	
	//For Accessing Issue Record Data
	public IssueRecord(int issueId, int userId, int bookId, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate, String status)
	{
		setIssueId(issueId);
		setUserId(userId);
		setBookId(bookId);
		setIssueDate(issueDate);
		setDueDate(dueDate);
		setReturnDate(returnDate);
		setStatus(status);
	}
	
	public IssueRecord(int issueId, int userId, int bookId, LocalDate issueDate, LocalDate dueDate, String status)
	{
		setIssueId(issueId);
		setUserId(userId);
		setBookId(bookId);
		setIssueDate(issueDate);
		setDueDate(dueDate);
		setStatus(status);
	}
	
	public int getIssueId() 
	{
		return issueId;
	}
	
	public void setIssueId(int issueId) 
	{
		this.issueId = issueId;
	}
	
	public int getUserId() 
	{
		return userId;
	}
	
	public void setUserId(int userId) 
	{
		if(userId <= 0)
			throw new IllegalArgumentException("User id must be valid");
		
		this.userId = userId;
	}
	
	public int getBookId() 
	{
		return bookId;
	}
	
	public void setBookId(int bookId) 
	{
		if(bookId <= 0)
			throw new IllegalArgumentException("Book id must be valid!");
		
		this.bookId = bookId;
	}
	
	public LocalDate getIssueDate() 
	{
		return issueDate;
	}
	
	public void setIssueDate(LocalDate issueDate) 
	{
		if(issueDate == null)
			throw new IllegalArgumentException("Date must be valid!");
		
		this.issueDate = issueDate;
	}
	
	public LocalDate getDueDate() 
	{
		return dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) 
	{
		if(dueDate == null)
			throw new IllegalArgumentException("Date must be valid!");
		
		if(dueDate.isBefore(issueDate))
		    throw new IllegalArgumentException("Due date cannot be before issue date!");
		
		this.dueDate = dueDate;
	}
	
	public LocalDate getReturnDate() 
	{
		return returnDate;
	}
	
	public void setReturnDate(LocalDate returnDate) 
	{	
		if(returnDate != null && returnDate.isBefore(issueDate))
		    throw new IllegalArgumentException("Return date cannot be before issue date!");
		
		this.returnDate = returnDate;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		if(status == null || status.isBlank())
			throw new IllegalArgumentException("Status cannot be empty!");
		
		if(status.strip().toLowerCase().equals(ISSUED_STATUS) || 
		   status.strip().toLowerCase().equals(RETURNED_STATUS)|| 
		   status.strip().toLowerCase().equals(OVERDUE_STATUS))
		{
			this.status = status.strip().toLowerCase();
		}
		else
			throw new IllegalArgumentException("Status must be valid!");
	}
	

	@Override
	public String toString() 
	{
		return "IssueRecord [issueId=" + issueId + ", userId=" + userId + ", bookId=" + bookId + ", issueDate="
				+ issueDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", status=" + status + "]";
	}

	
}
