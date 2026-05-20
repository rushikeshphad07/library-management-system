package com.java.librarymanagement.model;

public class Book 
{
	private int bookId; // AUTO_INCREMENT
	private String title;
	private String author;
	private String isbn;
	private int quantity;
	private int availableQuantity;

	public Book() 
	{
	}

	public Book(String title, String author, String isbn, int quantity) 
	{
		setTitle(title);
		setAuthor(author);
		setIsbn(isbn);
		setQuantity(quantity);
		this.availableQuantity = quantity;
	}

	// For Fetching
	public Book(String title, String author, String isbn, int quantity, int availableQuantity) 
	{
		setTitle(title);
		setAuthor(author);
		setIsbn(isbn);
		setQuantity(quantity);
		setAvailableQuantity(availableQuantity);
	}

	public int getBookId() 
	{
		return bookId;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		if (title == null || title.isBlank())
			throw new IllegalArgumentException("Title must be valid!");

		this.title = title.strip();
	}

	public String getAuthor() 
	{
		return author;
	}

	public void setAuthor(String author) 
	{
		if (author == null || author.isBlank())
			throw new IllegalArgumentException("Author name must be valid!");

		this.author = author.strip();
	}

	public String getIsbn() 
	{
		return isbn;
	}

	public void setIsbn(String isbn) 
	{
		if (isbn == null || isbn.isBlank())
			throw new IllegalArgumentException("ISBN number must be valid!");

		this.isbn = isbn.strip();
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		if (quantity < 0)
			throw new IllegalArgumentException("Quantity cannot be negative!");

		if (quantity < (this.quantity - this.availableQuantity))
			throw new IllegalArgumentException("Quantity cannot be less than issued books count!");

		this.quantity = quantity;

	}

	public int getAvailableQuantity() 
	{
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) 
	{
		if (availableQuantity < 0)
			throw new IllegalArgumentException("Available quantity cannot be negative!");

		if (availableQuantity > quantity)
			throw new IllegalArgumentException("Available quantity cannot exceed total quantity!");

		this.availableQuantity = availableQuantity;
	}

	@Override
	public String toString() 
	{
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", quantity="
				+ quantity + ", availableQuantity=" + availableQuantity + "]";
	}

}
