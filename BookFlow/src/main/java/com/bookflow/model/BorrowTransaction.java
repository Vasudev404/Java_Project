package com.bookflow.model;

import java.time.LocalDateTime;

public class BorrowTransaction {
    private int id;
    private String transactionId;
    private Book book;
    private User user;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public BorrowTransaction(int id, String transactionId, Book book, User user,
                             LocalDateTime borrowDate, LocalDateTime returnDate) {
        this.id = id;
        this.transactionId = transactionId;
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() { return id; }
    public String getTransactionId() { return transactionId; }
    public Book getBook() { return book; }
    public User getUser() { return user; }
    public LocalDateTime getBorrowDate() { return borrowDate; }
    public LocalDateTime getReturnDate() { return returnDate; }
}
