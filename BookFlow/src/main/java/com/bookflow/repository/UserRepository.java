package com.bookflow.repository;

import com.bookflow.model.Librarian;
import com.bookflow.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public User authenticate(String username, String password, String role) {
        String sql = "SELECT * FROM users WHERE username=? AND password=? AND role=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username); ps.setString(2, password); ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return createUser(rs);
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    public void add(User user) {
        String sql = "INSERT INTO users(user_id,name,contact,username,password,role) VALUES(?,?,?,?,?,?)";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,user.getUserId()); ps.setString(2,user.getName()); ps.setString(3,user.getContact());
            ps.setString(4,user.getUsername()); ps.setString(5,user.getPassword()); ps.setString(6,"USER");
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException("Could not register user: " + e.getMessage(), e); }
    }

    public List<User> search(String query) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role='USER' AND (user_id LIKE ? OR name LIKE ?) ORDER BY name";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            String q = "%" + query + "%"; ps.setString(1,q); ps.setString(2,q);
            ResultSet rs = ps.executeQuery(); while (rs.next()) list.add(createUser(rs));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    public User findById(String userId) {
        String sql = "SELECT * FROM users WHERE user_id=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,userId); ResultSet rs=ps.executeQuery(); if(rs.next()) return createUser(rs);
        } catch(SQLException e){throw new RuntimeException(e);} return null;
    }

    public int countActiveBorrowedBooks(int userDbId) {
        String sql = "SELECT COUNT(*) FROM transactions WHERE user_id=? AND return_date IS NULL";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userDbId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    private User createUser(ResultSet rs) throws SQLException {
        if ("LIBRARIAN".equals(rs.getString("role"))) {
            return new Librarian(rs.getInt("id"),rs.getString("user_id"),rs.getString("name"),rs.getString("contact"),rs.getString("username"),rs.getString("password"));
        }
        return new User(rs.getInt("id"),rs.getString("user_id"),rs.getString("name"),rs.getString("contact"),rs.getString("username"),rs.getString("password"));
    }
}
