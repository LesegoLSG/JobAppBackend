package com.tshiamiso.demo.services;

import com.tshiamiso.demo.dto.JwtAuthenticationResponse;
import com.tshiamiso.demo.dto.RefreshTokenRequest;
import com.tshiamiso.demo.dto.SignInRequest;
import com.tshiamiso.demo.dto.SignUpRequest;
import com.tshiamiso.demo.entities.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
