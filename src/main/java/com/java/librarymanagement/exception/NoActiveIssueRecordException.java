package com.java.librarymanagement.exception;

public class NoActiveIssueRecordException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	
	public NoActiveIssueRecordException(String message)
	{
		super(message);
	}
}
