package com.java.librarymanagement.model;

import java.util.Date;

public class IssueRecord 
{
	private int issueId;
	private int userId;
	private int bookId;
	private Date issueDate;
	private Date dueDate;
	private Date returnDate;
	private boolean status;
	
	public IssueRecord(int userId, int bookId, Date issueDate, Date dueDate, Date returnDate, boolean status)
	{
		this.userId = userId;
		this.bookId = bookId;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IssueRecord [issueId=" + issueId + ", userId=" + userId + ", bookId=" + bookId + ", issueDate="
				+ issueDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", status=" + status + "]";
	}

	
}
