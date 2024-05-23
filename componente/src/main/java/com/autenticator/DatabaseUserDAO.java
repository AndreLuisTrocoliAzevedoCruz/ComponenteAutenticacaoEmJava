package com.autenticator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
                String role = rs.getString("role");
                users.put(username, new User(username, password, role));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }
}