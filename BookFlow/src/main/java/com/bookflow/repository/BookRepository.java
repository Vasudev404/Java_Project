package com.bookflow.repository;

import com.bookflow.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public List<Book> search(String query) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ? OR genre LIKE ? ORDER BY title";
        try (Connection c=Database.getConnection(); PreparedStatement ps=c.prepareStatement(sql)) {
            String q="%"+query+"%"; for(int i=1;i<=4;i++) ps.setString(i,q);
            ResultSet rs=ps.executeQuery(); while(rs.next()) list.add(map(rs));
        } catch(SQLException e){throw new RuntimeException(e);} return list;
    }

    public Book findByIsbn(String isbn) {
        String sql="SELECT * FROM books WHERE isbn=?";
        try(Connection c=Database.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,isbn); ResultSet rs=ps.executeQuery(); if(rs.next()) return map(rs);
        }catch(SQLException e){throw new RuntimeException(e);} return null;
    }

    public Book findById(int id) {
        String sql="SELECT * FROM books WHERE id=?";
        try(Connection c=Database.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id); ResultSet rs=ps.executeQuery(); if(rs.next()) return map(rs);
        }catch(SQLException e){throw new RuntimeException(e);} return null;
    }

    public void add(Book b){
        String sql="INSERT INTO books(title,author,isbn,genre,status) VALUES(?,?,?,?,?)";
        try(Connection c=Database.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,b.getTitle());ps.setString(2,b.getAuthor());ps.setString(3,b.getIsbn());ps.setString(4,b.getGenre());ps.setString(5,b.getStatus());ps.executeUpdate();
        }catch(SQLException e){throw new RuntimeException("Could not add book: "+e.getMessage(),e);}
    }

    public void update(Book b){
        String sql="UPDATE books SET title=?,author=?,isbn=?,genre=? WHERE id=?";
        try(Connection c=Database.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,b.getTitle());ps.setString(2,b.getAuthor());ps.setString(3,b.getIsbn());ps.setString(4,b.getGenre());ps.setInt(5,b.getId());ps.executeUpdate();
        }catch(SQLException e){throw new RuntimeException("Could not update book: "+e.getMessage(),e);}
    }

    public void delete(int id){
        String sql="DELETE FROM books WHERE id=? AND status='AVAILABLE'";
        try(Connection c=Database.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){ps.setInt(1,id);ps.executeUpdate();}
        catch(SQLException e){throw new RuntimeException("Could not remove book: "+e.getMessage(),e);}
    }

    public void updateStatus(int id,String status){
        String sql="UPDATE books SET status=? WHERE id=?";
        try(Connection c=Database.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){ps.setString(1,status);ps.setInt(2,id);ps.executeUpdate();}
        catch(SQLException e){throw new RuntimeException(e);}
    }

    private Book map(ResultSet rs)throws SQLException{return new Book(rs.getInt("id"),rs.getString("title"),rs.getString("author"),rs.getString("isbn"),rs.getString("genre"),rs.getString("status"));}
}
