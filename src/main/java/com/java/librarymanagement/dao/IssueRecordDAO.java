package com.java.librarymanagement.dao;

import java.util.List;

import com.java.librarymanagement.model.IssueRecord;

public interface IssueRecordDAO 
{
	boolean issueBook(IssueRecord record);
	
	boolean returnBook(int userId, int bookId);
	
	IssueRecord getIssueById(int id);
	
	List<IssueRecord> getAllIssuedBooks();
	
	List<IssueRecord> getIssuesByUserId(int userId);
	
	List<IssueRecord> getOverdueBooks();
}
