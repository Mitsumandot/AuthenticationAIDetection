package com.example.authenticationforaidetection.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String JwtToken;
    private long expireIn;
    private String username;
}
