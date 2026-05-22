package com.java.librarymanagement.dao;

import java.awt.print.Book;
import java.util.List;

public interface BookDAO 
{
	boolean addBook(Book book);
	
	Book getBookById(int id);
	
	List<Book> getAllBooks();
	
	boolean updateBook(Book book);
	
	boolean deleteBook(Book book);
	
	boolean updateQuantity(int bookId, int quantity);
}
