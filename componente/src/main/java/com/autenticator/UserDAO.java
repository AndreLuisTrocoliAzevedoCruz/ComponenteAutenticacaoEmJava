package com.autenticator;

public interface UserDAO {
    
    User findByUsername(String username);
}