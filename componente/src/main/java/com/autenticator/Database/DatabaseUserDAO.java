package com.autenticator.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.autenticator.User;
import com.autenticator.Users.DefaultUser;

public class DatabaseUserDAO implements UserDAO {
    private Map<String, User> users = new HashMap<>();

    public DatabaseUserDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydatabase", "postgres", "mypassword");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String role = rs.getString("role");
                users.put(username, new DefaultUser(username, password, role, email, phoneNumber));
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydatabase", "postgres", "mypassword");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password, email, phone_number, role) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getRole());
            stmt.executeUpdate();
            users.put(user.getUsername(), user); // adiciona o novo usuário ao mapa
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }
}