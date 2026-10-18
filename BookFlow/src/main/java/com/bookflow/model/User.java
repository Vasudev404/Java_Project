package com.bookflow.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static final int MAX_BORROWED_BOOKS = 3;

    private int id;
    private String userId;
    private String name;
    private String contact;
    private String username;
    private String password;
    private List<Book> borrowedBooks;

    public User(int id, String userId, String name, String contact, String username, String password) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.username = username;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }

    public User(String userId, String name, String contact, String username, String password) {
        this(0, userId, name, contact, username, password);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(List<Book> books) { borrowedBooks = books; }

    public boolean canBorrowMore() {
        return borrowedBooks.size() < MAX_BORROWED_BOOKS;
    }

    public void borrowBook(Book book) { borrowedBooks.add(book); }
    public void returnBook(Book book) { borrowedBooks.remove(book); }

    @Override
    public String toString() {
        return userId + " - " + name;
    }
}
