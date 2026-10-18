package com.bookflow.model;

import com.bookflow.repository.BookRepository;
import com.bookflow.repository.TransactionRepository;
import com.bookflow.repository.UserRepository;
import java.util.List;

public class LibraryService {
    private final BookRepository bookRepo;
    private final UserRepository userRepo;
    private final TransactionRepository txRepo;

    public LibraryService(BookRepository bookRepo, UserRepository userRepo, TransactionRepository txRepo) {
        this.bookRepo=bookRepo; this.userRepo=userRepo; this.txRepo=txRepo;
    }

    public User login(String username,String password,String role){return userRepo.authenticate(username,password,role);}
    public void addBook(Book b){
        validateBook(b);
        bookRepo.add(b);
    }
    public void updateBook(Book b){
        validateBook(b);
        bookRepo.update(b);
    }
    public void removeBook(int id){bookRepo.delete(id);}
    public List<Book> searchBooks(String q){return bookRepo.search(q == null ? "" : q);}
    public void registerUser(User u){
        if(u.getUserId().isBlank() || u.getName().isBlank() || u.getUsername().isBlank() || u.getPassword().isBlank())
            throw new IllegalArgumentException("User ID, name, username and password are required.");
        userRepo.add(u);
    }
    public List<User> searchUsers(String q){return userRepo.search(q == null ? "" : q);}

    private void validateBook(Book b){
        if(b.getTitle().isBlank() || b.getAuthor().isBlank() || b.getIsbn().isBlank())
            throw new IllegalArgumentException("Title, author and ISBN are required.");
    }

    public String borrowBook(String isbn,String userId){
        Book book=bookRepo.findByIsbn(isbn);
        User user=userRepo.findById(userId);
        if(book==null) return "Book not found.";
        if(user==null) return "User not found.";
        if(!"AVAILABLE".equals(book.getStatus())) return "Book is already borrowed.";
        if(userRepo.countActiveBorrowedBooks(user.getId()) >= User.MAX_BORROWED_BOOKS) return "Borrowing limit reached. Maximum is " + User.MAX_BORROWED_BOOKS + " books.";
        String tx=txRepo.createBorrow(book.getId(),user.getId());
        bookRepo.updateStatus(book.getId(),"BORROWED");
        return "Borrow successful. Transaction: " + tx;
    }

    public String returnBook(String isbn,String userId){
        Book book=bookRepo.findByIsbn(isbn);
        User user=userRepo.findById(userId);
        if(book==null) return "Book not found.";
        if(user==null) return "User not found.";
        if(!txRepo.hasActiveBorrow(book.getId(),user.getId())) return "This user has not borrowed this book.";
        if(!txRepo.returnBook(book.getId(),user.getId())) return "Return could not be completed.";
        bookRepo.updateStatus(book.getId(),"AVAILABLE");
        return "Book returned successfully.";
    }
}
