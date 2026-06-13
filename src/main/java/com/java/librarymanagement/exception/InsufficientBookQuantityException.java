package com.java.librarymanagement.exception;

public class InsufficientBookQuantityException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	
	public InsufficientBookQuantityException(String message)
	{
		super(message);
	}
}
