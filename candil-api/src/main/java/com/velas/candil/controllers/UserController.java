package com.velas.candil.controllers;

import com.velas.candil.entities.user.User;
import com.velas.candil.models.user.AuthResponseDto;
import com.velas.candil.models.user.UserInformationDto;
import com.velas.candil.models.user.UserUpdateDto;
import com.velas.candil.services.file.FileService;
import com.velas.candil.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FileService fileService;

    @GetMapping()
    public ResponseEntity<List<UserInformationDto>> findAllUsersExceptSelf(Authentication authentication){
        return ResponseEntity.ok(userService.findAllUsersExceptSelf(authentication));
    }

    @GetMapping("/self")
    public ResponseEntity<UserInformationDto> findLoggedUsed(Authentication authentication){
        return ResponseEntity.ok(userService.getUserInformation(authentication));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserInformationDto>> findAllUsersContaining(@RequestParam("username") String username){
        return ResponseEntity.ok(userService.findUsersByUsernameContaining(username));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam String key,
                                                         @RequestPart MultipartFile file,
                                                         Authentication authentication) throws IOException {
        String imagePath = fileService.uploadProfileImageToS3(file, key, (User) authentication.getPrincipal());
        return ResponseEntity.ok(userService.updateUserImage(authentication, imagePath));
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponseDto> updateUsername(@RequestBody UserUpdateDto userUpdateDto,
                                                          Authentication authentication){

        return ResponseEntity.ok(userService.updateUsername(authentication, userUpdateDto));
    }
}
