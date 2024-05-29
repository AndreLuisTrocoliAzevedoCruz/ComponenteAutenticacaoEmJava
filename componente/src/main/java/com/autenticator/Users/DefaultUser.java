package com.autenticator.Users;

import com.autenticator.User;

public class DefaultUser implements User {
    private boolean blocked;
    private String username;
    private String password;
    private String role;
    private String email;
    private String phoneNumber;

    public DefaultUser(String username, String password, String role, String email, String phoneNumber) {

        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}