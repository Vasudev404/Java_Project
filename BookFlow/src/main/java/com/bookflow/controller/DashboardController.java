package com.bookflow.controller;

import com.bookflow.model.Book;
import com.bookflow.model.LibraryService;
import com.bookflow.model.User;
import com.bookflow.view.DashboardView;
import javax.swing.*;

public class DashboardController {
    private final DashboardView view; private final LibraryService service; private final User loggedIn;
    public DashboardController(DashboardView view,LibraryService service,User loggedIn){
        this.view=view;this.service=service;this.loggedIn=loggedIn;
        view.logoutButton.addActionListener(e->logout());
        view.bookSearchButton.addActionListener(e->searchBooks());
        view.borrowButton.addActionListener(e->borrow()); view.returnButton.addActionListener(e->returnBook());
        if(loggedIn instanceof com.bookflow.model.Librarian){
            view.addBookButton.addActionListener(e->addBook());view.updateBookButton.addActionListener(e->updateBook());view.removeBookButton.addActionListener(e->removeBook());
            view.userSearchButton.addActionListener(e->searchUsers());view.registerButton.addActionListener(e->registerUser());
        }
        searchBooks();
        view.bookTable.getSelectionModel().addListSelectionListener(e -> fillSelectedBook());
        if(loggedIn instanceof com.bookflow.model.Librarian) searchUsers();
    }
    public void show(){view.setVisible(true);}
    private void searchBooks(){view.showBooks(service.searchBooks(view.bookSearchField.getText().trim()));}
    private void borrow(){
        String userId=loggedIn instanceof com.bookflow.model.Librarian?view.userIdField.getText().trim():loggedIn.getUserId();
        if(userId.isEmpty()){JOptionPane.showMessageDialog(view,"Enter a User ID.");return;}
        JOptionPane.showMessageDialog(view,service.borrowBook(view.isbnField.getText().trim(),userId));searchBooks();
    }
    private void returnBook(){
        String userId=loggedIn instanceof com.bookflow.model.Librarian?view.userIdField.getText().trim():loggedIn.getUserId();
        if(userId.isEmpty()){JOptionPane.showMessageDialog(view,"Enter a User ID.");return;}
        JOptionPane.showMessageDialog(view,service.returnBook(view.isbnField.getText().trim(),userId));searchBooks();
    }
    private void fillSelectedBook(){
        int row=view.bookTable.getSelectedRow();
        if(row < 0) return;
        view.addTitle.setText(String.valueOf(view.bookTable.getValueAt(row,1)));
        view.addAuthor.setText(String.valueOf(view.bookTable.getValueAt(row,2)));
        view.addIsbn.setText(String.valueOf(view.bookTable.getValueAt(row,3)));
        view.addGenre.setText(String.valueOf(view.bookTable.getValueAt(row,4)));
    }
    private void addBook(){
        try{service.addBook(new Book(view.addTitle.getText().trim(),view.addAuthor.getText().trim(),view.addIsbn.getText().trim(),view.addGenre.getText().trim()));JOptionPane.showMessageDialog(view,"Book added.");clearBookForm();searchBooks();}
        catch(Exception e){JOptionPane.showMessageDialog(view,e.getMessage());}
    }
    private void updateBook(){
        int row=view.bookTable.getSelectedRow();if(row<0){JOptionPane.showMessageDialog(view,"Select a book first.");return;}
        int id=(Integer)view.bookTable.getValueAt(row,0);Book b=new Book(id,view.addTitle.getText().trim(),view.addAuthor.getText().trim(),view.addIsbn.getText().trim(),view.addGenre.getText().trim(),(String)view.bookTable.getValueAt(row,5));
        try{service.updateBook(b);JOptionPane.showMessageDialog(view,"Book updated.");searchBooks();}catch(Exception e){JOptionPane.showMessageDialog(view,e.getMessage());}
    }
    private void removeBook(){
        int row=view.bookTable.getSelectedRow();if(row<0){JOptionPane.showMessageDialog(view,"Select a book first.");return;}int id=(Integer)view.bookTable.getValueAt(row,0);
        try{service.removeBook(id);JOptionPane.showMessageDialog(view,"Book removed if it was available.");searchBooks();}catch(Exception e){JOptionPane.showMessageDialog(view,e.getMessage());}
    }
    private void searchUsers(){view.showUsers(service.searchUsers(view.userSearchField.getText().trim()));}
    private void registerUser(){
        try{service.registerUser(new User(view.regId.getText().trim(),view.regName.getText().trim(),view.regContact.getText().trim(),view.regUsername.getText().trim(),view.regPassword.getText()));JOptionPane.showMessageDialog(view,"User registered.");searchUsers();}
        catch(Exception e){JOptionPane.showMessageDialog(view,e.getMessage());}
    }
    private void clearBookForm(){view.addTitle.setText("");view.addAuthor.setText("");view.addIsbn.setText("");view.addGenre.setText("");}
    private void logout(){view.dispose();new LoginController(new com.bookflow.view.LoginView(),service).show();}
}
