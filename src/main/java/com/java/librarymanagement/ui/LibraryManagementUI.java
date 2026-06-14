package com.java.librarymanagement.ui;

import java.util.List;
import java.util.Scanner;

import com.java.librarymanagement.model.Book;
import com.java.librarymanagement.model.IssueRecord;
import com.java.librarymanagement.model.User;
import com.java.librarymanagement.service.BookService;
import com.java.librarymanagement.service.IssueService;
import com.java.librarymanagement.service.UserService;

public class LibraryManagementUI 
{
	private Scanner sc;
	
	private UserService userService;
	private BookService bookService;
	private IssueService issueService;
	
	private User currentUser;
	
	public LibraryManagementUI(UserService userService, BookService bookService, IssueService issueService, Scanner sc)
	{
		this.sc = sc;
		this.userService = userService;
		this.bookService = bookService;
		this.issueService = issueService;
	}
	
	public void start()
	{
		while(true)
		{
			System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====\n");
	        System.out.println("1. Login");
	        System.out.println("2. Register");
	        System.out.println("3. Exit");
	        System.out.print("Enter choice: ");		
	        
	        try
	        {
	        	int choice = Integer.parseInt(sc.nextLine());
	        	
	        	switch(choice)
	        	{
	        		case 1 -> login();
	        		
	        		case 2 -> register();
	        		
	        		case 3 -> 
	        		{
	        			System.out.println("Exiting..");
	        			return;
	        		}
	        		
	        		default -> System.out.println("Invalid choice!");
	        	}
	        }
	        catch(NumberFormatException e)
	        {
	        	System.out.println("Please enter a valid number!");
	        }
		}
	}
	
	private void register()
	{
		System.out.print("Name : ");
		String name = sc.nextLine();
		
		System.out.print("Email : ");
		String email = sc.nextLine();
		
		System.out.println("Password : ");
		String password = sc.nextLine();
		
		User user = new User(name, email, password, "MEMBER");
		
		try
		{
			if(userService.addUser(user))
			{
				currentUser = null;
				System.out.println("Registration successful!");
			}
			else
				System.out.println("Registration failed!");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void login()
	{
		try
		{
			System.out.print("Email : ");
			String email = sc.nextLine();
			
			System.out.print("Password : ");
			String password = sc.nextLine();
			
			currentUser = userService.login(email, password);
			System.out.println("Login Successful!");
			
			if("ADMIN".equalsIgnoreCase(currentUser.getRole()))
				adminMenu();
			else
				memberMenu();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void adminMenu() 
	{
		try
		{
			int choice;
			do
			{
				System.out.println("\n===== ADMIN MENU =====\n");
				System.out.println("1. Add Book");
				System.out.println("2. Update Book");
				System.out.println("3. Delete Book");
				System.out.println("4. Serach Book By Id");
				System.out.println("5. Search Book By ISBN");
				System.out.println("6. View All Books");
				System.out.println("7. View All Users");
				System.out.println("8. View All Issued Books");
				System.out.println("9. View Overdue Books");
				System.out.println("10. Logout");
				
				System.out.print("Enter Choice : ");
				choice = Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1 -> addBook();
					case 2 -> updateBook();
					case 3 -> deleteBook();
					case 4 -> searchBookById();
					case 5 -> searchBookByISBN();
					case 6 -> viewAllBooks();
					case 7 -> viewAllUsers();
					case 8 -> viewAllIssuedBooks();
					case 9 -> viewOverdueBooks();
					case 10 -> 
					{
						currentUser = null;
						System.out.println("Logged out successfully!");
					}
					default -> System.out.println("Invalid choice!");
				}
				
			} while(choice != 10);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	private void memberMenu()
	{
		try
		{
			int choice;
			do
			{
				System.out.println("\n===== MEMBER MENU =====\n");
				System.out.println("1. View All Books");
				System.out.println("2. Search Book By Id");
				System.out.println("3. Search Book By ISBN");
				System.out.println("4. Issue Book");
				System.out.println("5. Return Book");
				System.out.println("6. View My Issued Books");
				System.out.println("7. Logout");
				
				System.out.print("Enter choice : ");
				choice = Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1 -> viewAllBooks();
					case 2 -> searchBookById();
					case 3 -> searchBookByISBN();
					case 4 -> issueBook();
					case 5 -> returnBook();
					case 6 -> viewMyIssuedBooks();
					case 7 -> 
					{
						currentUser = null;
						System.out.println("Logged out successfully!");
					}
					default -> System.out.println("Invalid choice!");
				}
				
			} while(choice != 7);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void addBook()
	{
		try
		{
			System.out.println("\nAdding Book :- ");
			System.out.print("Enter Title : ");
			String title = sc.nextLine();
			
			System.out.print("Enter Author : ");
			String author = sc.nextLine();
			
			System.out.print("Enter ISBN : ");
			String isbn = sc.nextLine();
			
			System.out.print("Enter Quantity : ");
			int quantity = Integer.parseInt(sc.nextLine());
			
			Book book = new Book(title, author, isbn, quantity);
			
			if(bookService.addBook(book))
				System.out.println("Book added successfully!");
			else
				System.out.println("Failed to add book!");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void updateBook()
	{
		try
		{
			System.out.println("\nUpdating Book :- ");
			
			System.out.println("Enter BookID : ");
			int id = Integer.parseInt(sc.nextLine());
			
			Book existingBook = bookService.getBookById(id);
			System.out.println(existingBook);
			
			System.out.print("Enter New Title : ");
			String title = sc.nextLine();
			
			System.out.print("Enter New Author : ");
			String author = sc.nextLine();
			
			System.out.print("Enter New ISBN : ");
			String isbn = sc.nextLine();
			
			System.out.print("Enter New Quantity : ");
			int quantity = Integer.parseInt(sc.nextLine());
			
			Book updatedBook = new Book(id, title, author, isbn, quantity);
			
			if(bookService.updateBook(updatedBook))
				System.out.println("Book updated successfully!");
			else
				System.out.println("Failed to update book!");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void deleteBook()
	{
		try
		{
			System.out.println("\nDeleting Book :- ");
			System.out.print("Enter BookId : ");
			int id = Integer.parseInt(sc.nextLine());
			
			if(bookService.deleteBook(id))
			{
				System.out.println("Book deleted successfully!");
			}
			else
			{
				System.out.println("Failed to delete book!");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void searchBookById()
	{
		try
		{
			System.out.println("\nSearching Book By Id :- ");
			System.out.print("Enter BookId : ");
			int id = Integer.parseInt(sc.nextLine());
			
			Book book = bookService.getBookById(id);
			System.out.println(book);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void searchBookByISBN()
	{
		try
		{
			System.out.println("\nSearching Book By ISBN :- ");
			System.out.print("Enter ISBN : ");
			String isbn = sc.nextLine();
			
			Book book = bookService.getBookByISBN(isbn);
			System.out.println(book);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void viewAllBooks()
	{
		try
		{
			List<Book> allBooks = bookService.getAllBooks();
			
			if(allBooks.isEmpty())
			{
				System.out.println("No books found!");
				return;
			}
			
			System.out.println("\nAll Books :-");
			for(Book book : allBooks)
			{
				System.out.println(book);
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void viewAllUsers()
	{
		try
		{
			List<User> allUsers = userService.getAllUsers();	
			
			if(allUsers.isEmpty())
			{
				System.out.println("No users found!");
				return;
			}
			
			System.out.println("\nAll Users :-");
			for(User u : allUsers)
			{
				System.out.println(u);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void viewAllIssuedBooks()
	{
		try
		{
			List<IssueRecord> issueRecords = issueService.getAllIssuedBooks();
			
			if(issueRecords.isEmpty())
			{
				System.out.println("No issued books found!");
				return;
			}
			
			System.out.println("\nIssued Books :-");
			for(IssueRecord record : issueRecords)
			{
				System.out.println(record);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void viewOverdueBooks()
	{
		try
		{
			List<IssueRecord> overdueBooks = issueService.getOverdueBooks();
			
			if(overdueBooks.isEmpty())
			{
				System.out.println("No overdue books found!");
				return;
			}
			
			System.out.println("\nOverdue Books :- ");
			for(IssueRecord record : overdueBooks)
			{
				System.out.println(record);
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void issueBook()
	{
		try
		{
			System.out.println("\nIssuing Book :- ");
			
			System.out.print("Enter Book ID : ");
			int bookId = Integer.parseInt(sc.nextLine());
			
			Book book = bookService.getBookById(bookId);
			
			if(issueService.issueBook(bookId, currentUser.getUserId()))
				System.out.println(book.getTitle() + "borrowed successfully!");
			else
				System.out.println("Failed to borrow book!");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void returnBook()
	{
		try
		{
			System.out.println("\nReturning Book :- ");
			
			System.out.print("Enter Book ID : ");
			int bookId = Integer.parseInt(sc.nextLine());
			
			if(issueService.returnBook(currentUser.getUserId(), bookId))
				System.out.println("Book returned successfully!");
			else
				System.out.println("Failed to return book!");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void viewMyIssuedBooks()
	{
		try
		{			
			List<IssueRecord> issuedBooks = issueService.getIssuedBooksByUser(currentUser.getUserId());
			
			if(issuedBooks.isEmpty())
			{
				System.out.println("No issued book found!");
				return;
			}
			
			System.out.println("\nMy Issued Books :- ");
			for(IssueRecord record : issuedBooks)
			{
				System.out.println(record);
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}
