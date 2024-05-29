package com.autenticator.Database;

import com.autenticator.AuthenticationService;
import com.autenticator.Users.DefaultUser;

public class NewUser {

    private UserDAO userDAO;
    private AuthenticationService authenticationService;

    public NewUser(UserDAO userDAO, AuthenticationService authenticationService) {
        this.userDAO = userDAO;
        this.authenticationService = authenticationService;
    }

    public void registerUser(String login, String password, String email, String phoneNumber, String role) {
        if (!authenticationService.isAuthenticated() ||!authenticationService.getAuthenticatedUser().getRole().equals("admin")) {
            throw new RuntimeException("Somente administradores podem registrar novos usuários.");
        }
        DefaultUser user = new DefaultUser(login, password, role, email, phoneNumber);
        userDAO.saveUser(user);
    }

    public void registerAdminUser(String login, String password, String email, String phoneNumber) {
        registerUser(login, password, email, phoneNumber, "admin");
    }
    
}