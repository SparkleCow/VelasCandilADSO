package com.velas.candil.services;

import com.velas.candil.config.jwt.JwtUtils;
import com.velas.candil.entities.Role;
import com.velas.candil.entities.User;
import com.velas.candil.mappers.UserMapper;
import com.velas.candil.models.AuthRegisterDto;
import com.velas.candil.models.AuthResponseDto;
import com.velas.candil.models.RoleEnum;
import com.velas.candil.repositories.ActivateTokenRepository;
import com.velas.candil.repositories.RoleRepository;
import com.velas.candil.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final ActivateTokenRepository activateTokenRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;
    private final UserDetailsService userDetailsService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    @Override
    public AuthResponseDto login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User  user = (User) authentication.getPrincipal();
        String token = jwtUtils.generateToken(user);
        return new AuthResponseDto(token);
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public void register(AuthRegisterDto dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));

        Role userRole = roleRepository.findByRole(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(userRole);
        userRepository.save(user);
    }

    @Override
    public void sendValidationEmail(User user) {

    }

    @Override
    public String generateAndSaveToken(User user) {
        return "";
    }

    @Override
    public String generateToken(int length) {
        return "";
    }

    @Override
    public void activateAccount(String token) {

    }
}