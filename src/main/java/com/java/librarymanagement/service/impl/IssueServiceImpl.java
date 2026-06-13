package com.java.librarymanagement.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.java.librarymanagement.dao.BookDAO;
import com.java.librarymanagement.dao.IssueRecordDAO;
import com.java.librarymanagement.dao.UserDAO;
import com.java.librarymanagement.exception.BookNotFoundException;
import com.java.librarymanagement.exception.InsufficientBookQuantityException;
import com.java.librarymanagement.exception.UserNotFoundException;
import com.java.librarymanagement.model.Book;
import com.java.librarymanagement.model.IssueRecord;
import com.java.librarymanagement.model.User;
import com.java.librarymanagement.service.IssueService;

public class IssueServiceImpl implements IssueService
{
	
	private BookDAO bookDAO;
	private UserDAO userDAO;
	private IssueRecordDAO issueRecordDAO;

	public IssueServiceImpl(BookDAO bookDAO, UserDAO userDAO, IssueRecordDAO issueRecordDAO) 
	{
		
		if(bookDAO == null || userDAO == null || issueRecordDAO == null)
			throw new IllegalArgumentException("DAO cannot be null!");
		
		this.bookDAO = bookDAO;
		this.userDAO = userDAO;
		this.issueRecordDAO = issueRecordDAO;
	}

	@Override
	public boolean issueBook(int bookId, int userId) 
	{
		
		User user = userDAO.getUserById(userId);
		Book book = bookDAO.getBookById(bookId);
		
	    if(user == null)
	        throw new UserNotFoundException("User not found!");

	    if(book == null)
	        throw new BookNotFoundException("Book not found!");
	    
	    if(book.getAvailableQuantity() <= 0)
	    	throw new InsufficientBookQuantityException("Book is not available!");
	    
	    IssueRecord issueRecord = new IssueRecord
	    (
	    		userId,
	    		bookId,
	    		LocalDate.now(),
	    		LocalDate.now().plusDays(14)
	    );
		
		return issueRecordDAO.issueBook(issueRecord);
	}

	@Override
	public boolean returnBook(int issueId) 
	{
		
		if(issueId <= 0)
			throw new IllegalArgumentException("Invalid ID!");
		
		return issueRecordDAO.returnBook(issueId);
	}

	@Override
	public IssueRecord getIssueById(int issueId) 
	{
		if(issueId <= 0)
			throw new IllegalArgumentException("Invalid ID!");
		
		return issueRecordDAO.getIssueById(issueId);
	}

	@Override
	public List<IssueRecord> getAllIssuedBooks() 
	{
		return issueRecordDAO.getAllIssuedBooks();
	}

	@Override
	public List<IssueRecord> getIssuedBooksByUser(int userId) 
	{
		
		if(userId <= 0)
			throw new IllegalArgumentException("Invalid ID!");
		
		return issueRecordDAO.getIssuesByUserId(userId);
	}

	@Override
	public List<IssueRecord> getOverdueBooks() 
	{
		return issueRecordDAO.getOverdueBooks();
	}

}
