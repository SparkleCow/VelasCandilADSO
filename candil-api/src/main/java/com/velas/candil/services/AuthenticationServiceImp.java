package com.velas.candil.services;

import com.velas.candil.config.jwt.JwtUtils;
import com.velas.candil.entities.user.ActivateToken;
import com.velas.candil.entities.user.Role;
import com.velas.candil.entities.user.User;
import com.velas.candil.mappers.UserMapper;
import com.velas.candil.models.AuthRegisterDto;
import com.velas.candil.models.AuthResponseDto;
import com.velas.candil.models.EmailTemplate;
import com.velas.candil.models.RoleEnum;
import com.velas.candil.repositories.ActivateTokenRepository;
import com.velas.candil.repositories.RoleRepository;
import com.velas.candil.repositories.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final ActivateTokenRepository tokenRepository;
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
    public void register(AuthRegisterDto dto) throws MessagingException {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));

        Role userRole = roleRepository.findByRole(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(userRole);
        User userSaved = userRepository.save(user);
        sendValidationEmail(userSaved);
    }

    @Override
    public void sendValidationEmail(User user) throws MessagingException {
        String token = generateAndSaveToken(user);
        emailService.sendEmail(user.getEmail(), user.getUsername(), EmailTemplate.ACTIVATE_ACCOUNT,
                activationUrl, token, "Activate account");
    }

    @Override
    public String generateAndSaveToken(User user) {
        String generatedToken = generateToken(6);
        ActivateToken token = ActivateToken.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    @Override
    public String generateToken(int length) {
        String token = "1234567890";
        SecureRandom random = new SecureRandom();
        StringBuilder tokenSb = new StringBuilder();
        int indexRandom;
        for(int i=0;i<length;i++){
            indexRandom = random.nextInt(token.length());
            tokenSb.append(token.charAt(indexRandom));
        }
        return tokenSb.toString();
    }

    @Override
    public void activateAccount(String token) throws MessagingException {
        ActivateToken tokenResult = tokenRepository.findByToken(token).orElseThrow(RuntimeException::new);
        if(LocalDateTime.now().isAfter(tokenResult.getExpiredAt())){
            sendValidationEmail(tokenResult.getUser());
            throw new RuntimeException("Activation token has expired or is invalid");
        }
        User user = tokenResult.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        tokenResult.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(tokenResult);    }
}