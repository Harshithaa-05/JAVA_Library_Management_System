 Library Management System (Java Console Project)

Project Description

This project is a Java-based console application integrated with MySQL using JDBC to manage a library system.
It allows users to register, add books, issue and return books, and search for books.
The system also calculates fines for late returns, making it a complete basic database-driven library management solution.

Tools Used

Java
JDBC (Java Database Connectivity)
MySQL Database
Java Time API (LocalDate, ChronoUnit)


Project Components

Library Management Module
Book Management (Add, View, Search)
User Management (Register, View)
Issue & Return System
Fine Calculation System
Database Connectivity Module


Features

Add and manage books (stored in database)
Register and manage users
Issue books with due date tracking
Return books with late fine calculation
Search books by title or author
Display all books with availability status
Persistent data storage using MySQL

Key Functionalities
Books can be issued for 7 days
Late return fine is ₹5 per day
Tracks which user has borrowed a book
Prevents issuing unavailable books
Stores issue date and due date in database
Updates book status automatically on return


Database Setup
1. Create Database
CREATE DATABASE library_db;
USE library_db;


2. Create Users Table
CREATE TABLE users (
    user_id INT PRIMARY KEY,
    name VARCHAR(50)
);

3. Create Books Table
CREATE TABLE books (
    id INT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    is_issued BOOLEAN DEFAULT FALSE,
    issued_to_user_id INT,
    issue_date DATE,
    due_date DATE
);

 Database Connection

Create a file DBConnection.java:

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/library_db",
            "root",
            "your_password"
        );
    }
}

 How to Run
 
Open your IDE (Eclipse / IntelliJ / VS Code)
Add all Java files:
Main.java
Library.java
Book.java
User.java
DBConnection.java

Compile the program:

javac *.java

Run the program:

java Main


 Menu Options
 
Add Book
Show Books
Register User
Show Users
Issue Book
Return Book
Search by Title
Search by Author
Exit


 Screenshots

You can display screenshots in README like this:

![Add Book](screenshots/add book.png)
![Show Books](screenshots/show book.png)
![Issue Book,Return Book](screenshots/issue book,return book.png)
![Register user,Show user](screenshots/Register user,show user.png)
![Search by title,Search by author](screenshots/search by title,search by author.png)


Future Improvements

Add GUI using Java Swing or JavaFX
Add login authentication system
Track borrowing history
Generate reports
Deploy as a web application (Spring Boot + React)

 Author

Harshitha
