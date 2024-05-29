package com.autenticator.Database;

import com.autenticator.User;

public interface UserDAO {

    void saveUser(User user);
    User findByUsername(String username);
}