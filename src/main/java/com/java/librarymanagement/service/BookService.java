package com.java.librarymanagement.service;

import java.util.List;

import com.java.librarymanagement.model.Book;

public interface BookService 
{
	boolean addBook(Book book);
	
	Book getBookById(int id);
	
	Book getBookByISBN(String isbn);
	
	List<Book> getAllBooks();
	
	boolean updateBook(Book book);
	
	boolean deleteBook(int id);
	
	boolean updateQuantity(int bookId, int quantity);
}
