package com.bookflow.repository;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:bookflow.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() {
        String users = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, user_id TEXT UNIQUE NOT NULL, " +
                "name TEXT NOT NULL, contact TEXT, username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL, role TEXT NOT NULL)";
        String books = "CREATE TABLE IF NOT EXISTS books (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, author TEXT NOT NULL, " +
                "isbn TEXT UNIQUE NOT NULL, genre TEXT, status TEXT NOT NULL)";
        String transactions = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, transaction_id TEXT UNIQUE NOT NULL, " +
                "book_id INTEGER NOT NULL, user_id INTEGER NOT NULL, borrow_date TEXT NOT NULL, " +
                "return_date TEXT, FOREIGN KEY(book_id) REFERENCES books(id), FOREIGN KEY(user_id) REFERENCES users(id))";

        try (Connection c = getConnection(); Statement s = c.createStatement()) {
            s.execute(users);
            s.execute(books);
            s.execute(transactions);
            seedUsers(c);
            seedBooks(c);
        } catch (SQLException e) {
            throw new RuntimeException("Database initialization failed: " + e.getMessage(), e);
        }
    }

    private static void seedUsers(Connection c) throws SQLException {
        String sql = "INSERT OR IGNORE INTO users(user_id,name,contact,username,password,role) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            addUser(ps, "L001", "Main Librarian", "9999999999", "admin", "admin123", "LIBRARIAN");
            addUser(ps, "U001", "Demo User", "8888888888", "user1", "user123", "USER");
        }
    }

    private static void addUser(PreparedStatement ps, String id, String name, String contact,
                                String username, String password, String role) throws SQLException {
        ps.setString(1, id); ps.setString(2, name); ps.setString(3, contact);
        ps.setString(4, username); ps.setString(5, password); ps.setString(6, role); ps.executeUpdate();
    }

    private static void seedBooks(Connection c) throws SQLException {
        String sql = "INSERT OR IGNORE INTO books(title,author,isbn,genre,status) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            addBook(ps, "The Alchemist", "Paulo Coelho", "9780061122415", "Fiction");
            addBook(ps, "Clean Code", "Robert C. Martin", "9780132350884", "Programming");
            addBook(ps, "1984", "George Orwell", "9780451524935", "Dystopian");
        }
    }

    private static void addBook(PreparedStatement ps, String title, String author, String isbn, String genre) throws SQLException {
        ps.setString(1, title); ps.setString(2, author); ps.setString(3, isbn);
        ps.setString(4, genre); ps.setString(5, "AVAILABLE"); ps.executeUpdate();
    }
}
