package com.autenticator;

import java.util.HashMap;
import java.util.Map;

public class DatabaseUserDAO implements UserDAO {
    private Map<String, User> users = new HashMap<>();

    public DatabaseUserDAO() {
        
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }
}