package com.java.librarymanagement.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection 
{
	public static Connection getConnection()
	{
		Properties props = new Properties();
			
		try(InputStream in = DBConnection.class.getResourceAsStream("/db.properties"))
		{	
			if(in != null)
			{
				props.load(in);
				
				String driver = props.getProperty("db.driver");
				String url = props.getProperty("db.url");
				String username = props.getProperty("db.username");
				String password = props.getProperty("db.password");
				
				Class.forName(driver);
				
				Connection conn = DriverManager.getConnection(url, username, password);
				
				System.out.println("DB Connection Successful.");
				
				return conn;
			}
			else
			{
				throw new RuntimeException("db.properties file not found");
			}
		}
		catch(IOException e)
		{
			throw new RuntimeException("Unable to load properties file", e);
		}
		catch(ClassNotFoundException e)
		{
			throw new RuntimeException("JDBC Driver not found", e);
		}
		catch(SQLException e)
		{
			throw new RuntimeException("Database connection failed", e);
		}
	}
}

//Properties --> specialized map for configuration files(key->value)
//Here, props refers to empty property object

/*
 * getclassLoader() --> classpath context..
 *  
 * DBConnection.class --> Class object representing DBConnection class 
 * 						  or Class object containing metadata of DBConnection class 
 * 						  or Class<DBConnection>
 * 
 * getResourceAsStream() --> only requires classcontext(same package) 
 * 							 or classpathcontext(all folders)
 * 
 * .class --> class literal as it gives fixed, direct value known at compile time.
 */

/*
 * Load :- 
 * 		1.reads bytes/chars from the stream 
 * 		2.parses it into key-value pairs
 * 		3.stores them inside props
 */