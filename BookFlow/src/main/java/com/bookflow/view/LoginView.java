package com.bookflow.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField usernameField = new JTextField(18);
    public JPasswordField passwordField = new JPasswordField(18);
    public JButton librarianLoginButton = new JButton("Librarian Login");
    public JButton userLoginButton = new JButton("User Login");
    public JLabel messageLabel = new JLabel(" ");

    public LoginView() {
        setTitle("BookFlow - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8,8,8,8); g.fill=GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("BOOKFLOW", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        g.gridx=0;g.gridy=0;g.gridwidth=2;panel.add(title,g);
        g.gridwidth=1;
        addRow(panel,g,1,"Username:",usernameField);
        addRow(panel,g,2,"Password:",passwordField);
        JPanel buttons = new JPanel(); buttons.add(librarianLoginButton); buttons.add(userLoginButton);
        g.gridx=0;g.gridy=3;g.gridwidth=2;panel.add(buttons,g);
        g.gridy=4;messageLabel.setHorizontalAlignment(SwingConstants.CENTER);panel.add(messageLabel,g);
        g.gridy=5;panel.add(new JLabel("Demo: admin/admin123 | user1/user123",SwingConstants.CENTER),g);
        add(panel);
    }

    private void addRow(JPanel p,GridBagConstraints g,int y,String label,JComponent component){
        g.gridx=0;g.gridy=y;g.gridwidth=1;p.add(new JLabel(label),g);
        g.gridx=1;p.add(component,g);
    }
}
