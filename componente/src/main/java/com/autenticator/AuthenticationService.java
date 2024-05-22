package com.autenticator;

public interface AuthenticationService {
    
    boolean authenticate(String username, String password);
    boolean isAuthenticated();
    void logout();
}