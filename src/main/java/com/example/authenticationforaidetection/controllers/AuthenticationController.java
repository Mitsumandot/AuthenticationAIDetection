package com.example.authenticationforaidetection.controllers;

import com.example.authenticationforaidetection.dto.LoginUserDto;
import com.example.authenticationforaidetection.dto.RegisterUserDto;
import com.example.authenticationforaidetection.dto.VerifyUserDto;
import com.example.authenticationforaidetection.entities.User;
import com.example.authenticationforaidetection.responses.LoginResponse;
import com.example.authenticationforaidetection.responses.VerificationResponse;
import com.example.authenticationforaidetection.services.AuthenticationService;
import com.example.authenticationforaidetection.services.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final JWTService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto){
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = null;
        try {
            authenticatedUser = authenticationService.authenticate(loginUserDto);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime(), authenticatedUser.getUsername());
        return ResponseEntity.ok(loginResponse);

    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto){
        try {
            authenticationService.verifyUser(verifyUserDto);
            VerificationResponse verificationResponse = new VerificationResponse("Account Verified successfully");
            return ResponseEntity.ok(verificationResponse);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestBody String email){
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Check your email address for the new code");

        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
