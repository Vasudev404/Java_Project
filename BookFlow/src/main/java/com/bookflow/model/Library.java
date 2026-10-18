package com.bookflow.model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public List<Book> getBooks() { return books; }
    public List<User> getUsers() { return users; }
    public void setBooks(List<Book> books) { this.books = books; }
    public void setUsers(List<User> users) { this.users = users; }
}
