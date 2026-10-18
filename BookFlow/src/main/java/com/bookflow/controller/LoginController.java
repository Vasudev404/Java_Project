package com.bookflow.controller;

import com.bookflow.model.LibraryService;
import com.bookflow.model.User;
import com.bookflow.view.LoginView;
import javax.swing.*;

public class LoginController {
    private final LoginView view; private final LibraryService service;
    public LoginController(LoginView view, LibraryService service){this.view=view;this.service=service;view.librarianLoginButton.addActionListener(e->login("LIBRARIAN"));
        view.userLoginButton.addActionListener(e->login("USER"));}
    public void show(){view.setVisible(true);}
    private void login(String role){
        String username=view.usernameField.getText().trim();String password=new String(view.passwordField.getPassword());
        if(username.isEmpty()||password.isEmpty()){view.messageLabel.setText("Enter username and password.");return;}
        User user=service.login(username,password,role);
        if(user==null){view.messageLabel.setText("Invalid login or role.");return;}
        view.dispose();new DashboardController(new com.bookflow.view.DashboardView(user),service,user).show();
    }
}
