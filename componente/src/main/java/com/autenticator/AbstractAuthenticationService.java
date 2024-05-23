package com.autenticator;

public abstract class AbstractAuthenticationService implements AuthenticationService {
    protected UserDAO userDAO;
    protected User authenticatedUser;

    public AbstractAuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean authenticate(String username, String password) {

        User user = userDAO.findByUsername(username);

        if (user != null && user.getPassword().equals(password) && "admin".equals(user.getRole())) {
            authenticatedUser = user;
            return true;
        } else if (user != null && user.getPassword().equals(password)) {
            authenticatedUser = user;
            return true;
        }
        
        return false;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticatedUser != null;
    }

    @Override
    public void logout() {
        authenticatedUser = null;
    }
}