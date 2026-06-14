package com.java.librarymanagement.service;

import java.util.List;

import com.java.librarymanagement.model.IssueRecord;

public interface IssueService 
{
	boolean issueBook(int bookId, int userId);
	
	boolean returnBook(int userId, int bookId);
	
	IssueRecord getIssueById(int issueId);
	
	List<IssueRecord> getAllIssuedBooks();
	
	List<IssueRecord> getIssuedBooksByUser(int userId);
	
	List<IssueRecord> getOverdueBooks();
}
