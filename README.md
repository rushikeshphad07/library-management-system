# Library Management System

A console-based Library Management System built using Java, JDBC, MySQL, and Maven. The application follows a layered architecture and provides functionality for managing books, users, and book issue/return operations.

## Features

### Authentication & Authorization

* User login authentication
* Role-based access control (Admin/User)
* Admin accounts are created manually in the database
* Different functionalities are available based on user roles

### User Management

* Add users
* Update user details
* Delete users
* Search users
* View user information

### Book Management

* Add books
* Update book details
* Delete books
* Search books
* View all books
* Track available book quantity

### Book Issue & Return

* Issue books to users
* Return issued books
* Track issue date, due date, and return date
* View issued books
* View pending returns

### Validation & Exception Handling

* Input validation
* Business rule validation
* Custom exception handling
* Database error handling

## Technologies Used

* Java
* JDBC
* MySQL
* Maven
* Eclipse IDE

## Project Architecture

```
UI Layer
    ↓
Service Layer
    ↓
DAO Layer
    ↓
MySQL Database
```

### UI Layer

Handles user interaction and menu navigation.

### Service Layer

Contains business logic and validations.

### DAO Layer

Performs database operations using JDBC.

### Database Layer

Stores books, users, and issue records.

## Project Structure

```
src/main/java
│
├── model
├── dao
├── service
├── ui
├── util
├── exception
└── main
```

## Database Tables

### Users

Stores user information and roles.

### Books

Stores book details and inventory information.

### Issue Records

Stores book issue and return transactions.

## Prerequisites

* Java 8 or above
* MySQL Server
* Maven

## Setup Instructions

1. Clone the repository

```bash
git clone <repository-url>
```

2. Create a MySQL database

```sql
CREATE DATABASE library_management;
```

3. Create the required tables.

4. Configure database connection details in the project.

5. Run the application.

## Key Concepts Implemented

* Object-Oriented Programming (OOP)
* Layered Architecture
* JDBC
* MySQL Integration
* CRUD Operations
* Exception Handling
* Authentication & Authorization
* Service-Oriented Design
* Input Validation
* Maven Project Structure

## Learning Outcomes

This project helped in understanding:

* Java application architecture
* Database connectivity using JDBC
* SQL query execution
* Separation of concerns
* Service and DAO design patterns
* Exception handling strategies
* Real-world CRUD application development

## Author

Rushikesh Phad

Java Backend Developer (Fresher)
