package com.velas.candil.services;

import com.velas.candil.entities.user.User;
import com.velas.candil.models.user.AuthRegisterDto;
import com.velas.candil.models.user.AuthResponseDto;
import jakarta.mail.MessagingException;

/**
 * Service responsible for handling authentication and account lifecycle operations.
 * This includes login, logout, user registration, email validation flow,
 * and token generation for both JWT and email verification purposes.
 */
public interface AuthenticationService {

    AuthResponseDto login(String username, String password);
    void register(AuthRegisterDto authRegisterDto) throws MessagingException;
    void sendValidationEmail(User user) throws MessagingException;
    String generateAndSaveToken(User user);
    String generateToken(int length);
    /**
     * Activates a user account using an email verification token.
     * The provided token is an email validation token,
     * not a JWT.
     */
    void activateAccount(String token) throws MessagingException;
}
