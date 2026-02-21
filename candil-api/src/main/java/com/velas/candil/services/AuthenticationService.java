package com.velas.candil.services;

import com.velas.candil.entities.User;
import com.velas.candil.models.AuthRegisterDto;
import com.velas.candil.models.AuthResponseDto;

/**
 * Service responsible for handling authentication and account lifecycle operations.
 * This includes login, logout, user registration, email validation flow,
 * and token generation for both JWT and email verification purposes.
 */
public interface AuthenticationService {

    AuthResponseDto login(String username, String password);
    /**
     * Performs logout logic.
     * Depending on the implementation, this may invalidate tokens,
     * clear security context, or handle token blacklisting.
     */
    void logout(String token);
    void register(AuthRegisterDto authRegisterDto);
    void sendValidationEmail(User user);
    String generateAndSaveToken(User user);
    String generateToken(int length);
    /**
     * Activates a user account using an email verification token.
     * The provided token is an email validation token,
     * not a JWT.
     */
    void activateAccount(String token);
}
