package com.bookflow.repository;

import com.bookflow.model.BorrowTransaction;
import com.bookflow.model.Book;
import com.bookflow.model.User;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionRepository {
    public String createBorrow(int bookId, int userDbId) {
        String tx = "TX-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        String sql="INSERT INTO transactions(transaction_id,book_id,user_id,borrow_date) VALUES(?,?,?,?)";
        try(Connection c=Database.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,tx);ps.setInt(2,bookId);ps.setInt(3,userDbId);ps.setString(4,LocalDateTime.now().toString());ps.executeUpdate();
            return tx;
        }catch(SQLException e){throw new RuntimeException(e);}
    }

    public boolean returnBook(int bookId, int userDbId) {
        String sql="UPDATE transactions SET return_date=? WHERE book_id=? AND user_id=? AND return_date IS NULL";
        try(Connection c=Database.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,LocalDateTime.now().toString());ps.setInt(2,bookId);ps.setInt(3,userDbId);return ps.executeUpdate()>0;
        }catch(SQLException e){throw new RuntimeException(e);}
    }

    public boolean hasActiveBorrow(int bookId, int userDbId) {
        String sql="SELECT 1 FROM transactions WHERE book_id=? AND user_id=? AND return_date IS NULL";
        try(Connection c=Database.getConnection();PreparedStatement ps=c.prepareStatement(sql)){ps.setInt(1,bookId);ps.setInt(2,userDbId);return ps.executeQuery().next();}
        catch(SQLException e){throw new RuntimeException(e);}
    }
}
