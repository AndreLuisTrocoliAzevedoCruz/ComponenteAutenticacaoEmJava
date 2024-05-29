package com.autenticator.Database;

import com.autenticator.AuthenticationService;
import com.autenticator.User;

public abstract class AbstractAuthenticationService implements AuthenticationService {
    protected UserDAO userDAO;
    protected User authenticatedUser;
    private int maxAttempts = 3;
    private int attempts = 0;

    public AbstractAuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);

        if (user!= null && user.getPassword().equals(password)) {
            if (attempts >= maxAttempts) {
                user.setBlocked(true); // bloquear a conta do usuário
                userDAO.saveUser(user);
                return false;
            }
            authenticatedUser = user;
            attempts = 0;
            return true;

        } else {
            attempts++;
            return false;
        }
    }

    @Override
    public boolean isAuthenticated() {
        return authenticatedUser != null;
    }

    @Override
    public void logout() {
        authenticatedUser = null;
    }

    @Override
    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}