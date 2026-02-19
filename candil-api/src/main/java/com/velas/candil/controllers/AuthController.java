package com.velas.candil.controllers;

import com.velas.candil.models.AuthLoginDto;
import com.velas.candil.models.AuthRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Tag(name = "Authentication", description = "Operations related to authentication (Login - register - validation)")
public class AuthController {

    @Operation(
            summary = "Authenticate user",
            description = "Validates user credentials and returns a JWT token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authentication"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "400", description = "Malformed request")
    })
    @PostMapping("/login")
    public ResponseEntity<Void> login(
                            @Parameter(description = "User credentials", required = true)
                            @RequestBody AuthLoginDto authLoginDto){
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Register user",
            description = "Register a new user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Register successful"),
            @ApiResponse(responseCode = "400", description = "Malformed request")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Parameter(description = "User information for register", required = true)
            @RequestBody @Valid AuthRegisterDto authRegisterDto){
        return ResponseEntity.ok().build();
    }
}
