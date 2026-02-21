package com.velas.candil.services;

import com.velas.candil.entities.User;

public interface AuthenticationService {

    void login(String username, String password);
    String register(User user);
    String resetPassword(User user);
    String sendEmail();
}
