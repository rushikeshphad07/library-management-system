package com.java.librarymanagement.service.impl;

import java.util.List;

import com.java.librarymanagement.dao.BookDAO;
import com.java.librarymanagement.exception.BookAlreadyExistsException;
import com.java.librarymanagement.exception.BookNotFoundException;
import com.java.librarymanagement.model.Book;
import com.java.librarymanagement.service.BookService;

public class BookServiceImpl implements BookService
{
	
	private BookDAO bookDAO;
	public BookServiceImpl(BookDAO bookDAO)
	{
		
		if(bookDAO == null)
			throw new IllegalArgumentException("DAO cannot be null!");
		
		this.bookDAO = bookDAO;
	}

	@Override
	public boolean addBook(Book book) 
	{	
		if(book == null)
			throw new IllegalArgumentException("Book cannot be null!");
		
		Book existingBook = bookDAO.getBookByISBN(book.getIsbn());
		
		if(existingBook != null)
			throw new BookAlreadyExistsException("Book already exists!");
		
		return bookDAO.addBook(book);
	}

	@Override
	public Book getBookById(int id) 
	{
		if(id <= 0)
			throw new IllegalArgumentException("Invalid Book Id!");
		
		return bookDAO.getBookById(id);
	}

	@Override
	public List<Book> getAllBooks() 
	{
		return bookDAO.getAllBooks();
	}

	@Override
	public boolean updateBook(Book book) 
	{
		
		if(book == null)
			throw new IllegalArgumentException("Book cannot be null!");
		
		if(bookDAO.getBookById(book.getBookId()) == null)
			throw new BookNotFoundException("Book not found!");
		
		return bookDAO.updateBook(book);
	}

	@Override
	public boolean deleteBook(int id) 
	{
		
		if(id <= 0)
			throw new IllegalArgumentException("Invalid id!");
		
		return bookDAO.deleteBook(id);
	}

	@Override
	public boolean updateQuantity(int bookId, int quantity) 
	{
		
		if(bookId <= 0)
			throw new IllegalArgumentException("Invalid id!");
		
		if(quantity < 0)
			throw new IllegalArgumentException("Invalid quantity!");
		
		return bookDAO.updateQuantity(bookId, quantity);
	}
	
}
