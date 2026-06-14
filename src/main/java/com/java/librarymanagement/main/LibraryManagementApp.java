package com.java.librarymanagement.main;

import java.util.Scanner;

import com.java.librarymanagement.dao.BookDAO;
import com.java.librarymanagement.dao.IssueRecordDAO;
import com.java.librarymanagement.dao.UserDAO;
import com.java.librarymanagement.dao.impl.BookDAOImpl;
import com.java.librarymanagement.dao.impl.IssueRecordDAOImpl;
import com.java.librarymanagement.dao.impl.UserDAOImpl;
import com.java.librarymanagement.service.BookService;
import com.java.librarymanagement.service.IssueService;
import com.java.librarymanagement.service.UserService;
import com.java.librarymanagement.service.impl.BookServiceImpl;
import com.java.librarymanagement.service.impl.IssueServiceImpl;
import com.java.librarymanagement.service.impl.UserServiceImpl;
import com.java.librarymanagement.ui.LibraryManagementUI;

public class LibraryManagementApp 
{
    public static void main(String[] args) 
    {
    	
    	Scanner sc = new Scanner(System.in);
    	
    	UserDAO userDAO = new UserDAOImpl();
    	BookDAO bookDAO = new BookDAOImpl();
    	IssueRecordDAO issueRecordDAO = new IssueRecordDAOImpl();
    	
    	UserService userService = new UserServiceImpl(userDAO);
    	BookService bookService = new BookServiceImpl(bookDAO);
    	IssueService issueService = new IssueServiceImpl(bookDAO, userDAO, issueRecordDAO);
    	
    	LibraryManagementUI ui = new LibraryManagementUI(userService, bookService, issueService, sc);
    	
    	ui.start();
    }
}
