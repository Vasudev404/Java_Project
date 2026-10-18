package com.bookflow.model;

public class Librarian extends User {
    public Librarian(int id, String userId, String name, String contact, String username, String password) {
        super(id, userId, name, contact, username, password);
    }

    public Librarian(String userId, String name, String contact, String username, String password) {
        super(userId, name, contact, username, password);
    }
}
