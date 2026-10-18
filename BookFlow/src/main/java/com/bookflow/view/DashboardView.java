package com.bookflow.view;

import com.bookflow.model.Book;
import com.bookflow.model.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardView extends JFrame {
    public JButton logoutButton=new JButton("Logout");
    public JTextField bookSearchField=new JTextField(18);
    public JButton bookSearchButton=new JButton("Search");
    public JTable bookTable=new JTable();
    public JTextField isbnField=new JTextField(15);
    public JTextField userIdField=new JTextField(15);
    public JButton borrowButton=new JButton("Borrow");
    public JButton returnButton=new JButton("Return");

    public JTextField addTitle=new JTextField(12), addAuthor=new JTextField(12), addIsbn=new JTextField(12), addGenre=new JTextField(12);
    public JButton addBookButton=new JButton("Add Book"), updateBookButton=new JButton("Update Selected"), removeBookButton=new JButton("Remove Selected");
    public JTextField userSearchField=new JTextField(15); public JButton userSearchButton=new JButton("Search Users"); public JTable userTable=new JTable();
    public JTextField regId=new JTextField(10), regName=new JTextField(10), regContact=new JTextField(10), regUsername=new JTextField(10), regPassword=new JTextField(10);
    public JButton registerButton=new JButton("Register User");

    private boolean librarian;

    public DashboardView(User user) {
        librarian = user instanceof com.bookflow.model.Librarian;
        setTitle("BookFlow - " + (librarian ? "Librarian Dashboard" : "User Dashboard"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setSize(1000,650); setLocationRelativeTo(null);

        JPanel top=new JPanel(new BorderLayout());
        JLabel welcome=new JLabel("  Welcome, "+user.getName()+"  "); welcome.setFont(new Font("SansSerif",Font.BOLD,18));
        top.add(welcome,BorderLayout.WEST); top.add(logoutButton,BorderLayout.EAST); add(top,BorderLayout.NORTH);

        JTabbedPane tabs=new JTabbedPane();
        tabs.addTab("Search Books",buildBookPanel());
        tabs.addTab("Circulation",buildCirculationPanel());
        if(librarian){tabs.addTab("Book Management",buildBookManagement());tabs.addTab("User Management",buildUserManagement());}
        add(tabs,BorderLayout.CENTER);
    }

    private JPanel buildBookPanel(){
        JPanel p=new JPanel(new BorderLayout(10,10)); p.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        JPanel search=new JPanel(); search.add(new JLabel("Title / Author / ISBN / Genre:"));search.add(bookSearchField);search.add(bookSearchButton);p.add(search,BorderLayout.NORTH);
        bookTable.setModel(new DefaultTableModel(new Object[]{"ID","Title","Author","ISBN","Genre","Status"},0));
        p.add(new JScrollPane(bookTable),BorderLayout.CENTER);return p;
    }

    private JPanel buildCirculationPanel(){
        JPanel p=new JPanel(new GridBagLayout()); GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(10,10,10,10);g.fill=GridBagConstraints.HORIZONTAL;
        g.gridx=0;g.gridy=0;p.add(new JLabel("ISBN:"),g);g.gridx=1;p.add(isbnField,g);
        g.gridx=0;g.gridy=1;p.add(new JLabel("User ID:"),g);g.gridx=1;p.add(userIdField,g);
        JPanel buttons=new JPanel();buttons.add(borrowButton);buttons.add(returnButton);g.gridx=0;g.gridy=2;g.gridwidth=2;p.add(buttons,g);
        g.gridy=3;p.add(new JLabel("Librarians can enter any registered User ID. Users should use their own ID."),g);return p;
    }

    private JPanel buildBookManagement(){
        JPanel p=new JPanel(new BorderLayout()); JPanel form=new JPanel(new GridLayout(2,5,5,5));
        form.add(new JLabel("Title"));form.add(new JLabel("Author"));form.add(new JLabel("ISBN"));form.add(new JLabel("Genre"));form.add(new JLabel("Actions"));
        form.add(addTitle);form.add(addAuthor);form.add(addIsbn);form.add(addGenre);JPanel actions=new JPanel();actions.add(addBookButton);actions.add(updateBookButton);actions.add(removeBookButton);form.add(actions);
        p.add(form,BorderLayout.NORTH);p.add(new JScrollPane(bookTable),BorderLayout.CENTER);return p;
    }

    private JPanel buildUserManagement(){
        JPanel p=new JPanel(new BorderLayout(10,10));
        JPanel top=new JPanel();top.add(new JLabel("User ID / Name:"));top.add(userSearchField);top.add(userSearchButton);p.add(top,BorderLayout.NORTH);
        userTable.setModel(new DefaultTableModel(new Object[]{"ID","User ID","Name","Contact","Username"},0));p.add(new JScrollPane(userTable),BorderLayout.CENTER);
        JPanel reg=new JPanel();reg.setBorder(BorderFactory.createTitledBorder("Register New User"));reg.add(new JLabel("ID"));reg.add(regId);reg.add(new JLabel("Name"));reg.add(regName);reg.add(new JLabel("Contact"));reg.add(regContact);reg.add(new JLabel("Username"));reg.add(regUsername);reg.add(new JLabel("Password"));reg.add(regPassword);reg.add(registerButton);p.add(reg,BorderLayout.SOUTH);return p;
    }

    public void showBooks(List<Book> books){DefaultTableModel m=(DefaultTableModel)bookTable.getModel();m.setRowCount(0);for(Book b:books)m.addRow(new Object[]{b.getId(),b.getTitle(),b.getAuthor(),b.getIsbn(),b.getGenre(),b.getStatus()});}
    public void showUsers(List<User> users){DefaultTableModel m=(DefaultTableModel)userTable.getModel();m.setRowCount(0);for(User u:users)m.addRow(new Object[]{u.getId(),u.getUserId(),u.getName(),u.getContact(),u.getUsername()});}
}
