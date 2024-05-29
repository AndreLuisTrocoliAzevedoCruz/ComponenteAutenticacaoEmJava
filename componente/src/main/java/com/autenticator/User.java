package com.autenticator;

public interface User {
    
    String getUsername();
    String getPassword();
    String getRole();
    String getEmail();
    String getPhoneNumber();
    boolean isBlocked();
    void setBlocked(boolean blocked); // adicionado método setBlocked
    
}