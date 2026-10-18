package com.bookflow.controller;

import com.bookflow.model.LibraryService;
import com.bookflow.repository.BookRepository;
import com.bookflow.repository.Database;
import com.bookflow.repository.TransactionRepository;
import com.bookflow.repository.UserRepository;
import com.bookflow.view.LoginView;
import javax.swing.*;

public class BookFlowApplication {
    public static void main(String[] args) {
        Database.initialize();
        LibraryService service = new LibraryService(new BookRepository(), new UserRepository(), new TransactionRepository());
        SwingUtilities.invokeLater(() -> new LoginController(new LoginView(), service).show());
    }
}
