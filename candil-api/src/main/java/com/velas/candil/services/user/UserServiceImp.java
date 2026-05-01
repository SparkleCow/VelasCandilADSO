package com.velas.candil.services.user;

import com.velas.candil.config.jwt.JwtUtils;
import com.velas.candil.entities.user.User;
import com.velas.candil.models.user.AuthResponseDto;
import com.velas.candil.models.user.UserInformationDto;
import com.velas.candil.models.user.UserUpdateDto;
import com.velas.candil.repositories.UserRepository;
import com.velas.candil.services.aws.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final JwtUtils jwtUtils;

    private UserInformationDto mapToDto(User user, boolean includePresigned) {

        String imageUrl = user.getImageUrl();

        if (includePresigned && imageUrl != null) {
            imageUrl = s3Service.generatePresignedDownloadUrl(
                    imageUrl,
                    Duration.ofMinutes(30)
            );
        }

        return new UserInformationDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName() + " " + user.getLastName(),
                imageUrl,
                user.getRoles()
                        .stream()
                        .map(role -> role.getRole().name())
                        .collect(Collectors.toSet())
        );
    }

    private User getAuthenticatedUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

    @Override
    public UserInformationDto getUserInformation(Authentication authentication) {
        return mapToDto(getAuthenticatedUser(authentication), true);
    }

    @Override
    public String updateUserImage(Authentication authentication, String imagePath) {
        User user = getAuthenticatedUser(authentication);

        if(imagePath == null || imagePath.isEmpty()){
            return "Profile imaged could not be updated";
        }
        user.setImageUrl(imagePath);
        userRepository.save(user);
        return "Profile imaged updated successfully";
    }

    @Override
    public AuthResponseDto updateUsername(Authentication authentication, UserUpdateDto userUpdateDto) {
        String username = userUpdateDto.username();
        User user = getAuthenticatedUser(authentication);

        if (StringUtils.hasText(username) && !username.equals(user.getUsername())) {
            user.setUsername(username);
            userRepository.save(user);
        }

        //It creates a new token with the new username.
        String token  = jwtUtils.generateToken(user);
        return new AuthResponseDto(token);
    }

    @Override
    public UserInformationDto findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapToDto(user, false);
    }

    @Override
    public List<UserInformationDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(x-> this.mapToDto(x, false))
                .toList();
    }

    @Override
    public List<UserInformationDto> findAllUsersExceptSelf(Authentication authentication) {
        User currentUser = getAuthenticatedUser(authentication);

        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .map(x-> this.mapToDto(x, false))
                .toList();
    }

    @Override
    public List<UserInformationDto> findUsersByUsernameContaining(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username)
                .stream()
                .map(x-> this.mapToDto(x, false))
                .toList();
    }
}