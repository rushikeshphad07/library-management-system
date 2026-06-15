package com.java.librarymanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.librarymanagement.dao.BookDAO;
import com.java.librarymanagement.model.Book;
import com.java.librarymanagement.util.DBConnection;

public class BookDAOImpl implements BookDAO
{
	
	@Override
	public boolean addBook(Book book)
	{
		String query = "INSERT INTO books(title, author, isbn, quantity, available_quantity) VALUES(?, ?, ?, ?, ?)";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getIsbn());
			pstmt.setInt(4, book.getQuantity());
			pstmt.setInt(5, book.getAvailableQuantity());
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public Book getBookById(int id)
	{
		String query = "SELECT * FROM books WHERE id=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				Book book = new Book
				(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("isbn"),
						rs.getInt("quantity"),
						rs.getInt("available_quantity")
				);
				
				return book;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Book getBookByISBN(String isbn)
	{
		String query = "SELECT * FROM books WHERE isbn=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				Book book = new Book
				(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("isbn"),
						rs.getInt("quantity"),
						rs.getInt("available_quantity")
				);
				
				return book;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Book> getAllBooks()
	{
		String query = "SELECT * FROM books";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			
			List<Book> bookList = new ArrayList<Book>();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Book book = new Book
				(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("isbn"),
						rs.getInt("quantity"),
						rs.getInt("available_quantity")
				);
				
				bookList.add(book);
			}
			
			return bookList;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	@Override
	public boolean updateBook(Book book)
	{
		String query = "UPDATE books SET title=?, author=?, isbn=?, quantity=?, available_quantity=? WHERE id=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);	
		)
		{
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getIsbn());
			pstmt.setInt(4, book.getQuantity());
			pstmt.setInt(5, book.getAvailableQuantity());
			pstmt.setInt(6, book.getBookId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean deleteBook(int id)
	{
		String query = "DELETE FROM books WHERE id=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setInt(1, id);
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean updateQuantity(int bookId, int quantity)
	{
		String query = "UPDATE books SET quantity=? WHERE id=?";
		
		try
		(
				Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		)
		{
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, bookId);
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
