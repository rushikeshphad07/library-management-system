package com.java.librarymanagement.ui;

import java.util.Scanner;

import com.java.librarymanagement.dao.BookDAO;
import com.java.librarymanagement.dao.IssueRecordDAO;
import com.java.librarymanagement.dao.UserDAO;
import com.java.librarymanagement.dao.impl.BookDAOImpl;
import com.java.librarymanagement.dao.impl.IssueRecordDAOImpl;
import com.java.librarymanagement.dao.impl.UserDAOImpl;
import com.java.librarymanagement.model.User;
import com.java.librarymanagement.service.BookService;
import com.java.librarymanagement.service.IssueService;
import com.java.librarymanagement.service.UserService;
import com.java.librarymanagement.service.impl.BookServiceImpl;
import com.java.librarymanagement.service.impl.IssueServiceImpl;
import com.java.librarymanagement.service.impl.UserServiceImpl;

public class LibraryManagementUI 
{
	private Scanner sc;
	
	private UserService userService;
	private BookService bookService;
	private IssueService issueService;
	
	public LibraryManagementUI()
	{
		sc = new Scanner(System.in);
		
		UserDAO userDAO = new UserDAOImpl();
		BookDAO bookDAO = new BookDAOImpl();
		IssueRecordDAO issueRecordDAO = new IssueRecordDAOImpl();
		
		userService = new UserServiceImpl(userDAO);
		bookService = new BookServiceImpl(bookDAO);
		issueService = new IssueServiceImpl(bookDAO, userDAO ,issueRecordDAO);
	}
	
	public void start()
	{
		while(true)
		{
			System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice)
			{
				case 1:
					login();
					break;
					
				case 2:
					register();
					break;
					
				case 3:
					return;
					
				default:
					System.out.println("Invalid Choice!");
			}
		}
	}
}
