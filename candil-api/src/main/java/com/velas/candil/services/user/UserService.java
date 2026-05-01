package com.velas.candil.services.user;

import com.velas.candil.models.user.AuthResponseDto;
import com.velas.candil.models.user.UserInformationDto;
import com.velas.candil.models.user.UserUpdateDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserInformationDto getUserInformation(Authentication authentication);

    UserInformationDto findUserByUsername(String username);

    List<UserInformationDto> findAllUsers();
    List<UserInformationDto> findAllUsersExceptSelf(Authentication authentication);
    List<UserInformationDto> findUsersByUsernameContaining(String username);

    String updateUserImage(Authentication authentication, String imagePath);
    AuthResponseDto updateUsername(Authentication authentication, UserUpdateDto userUpdateDto);
}

