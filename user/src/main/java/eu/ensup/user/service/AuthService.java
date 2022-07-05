package eu.ensup.user.service;

import eu.ensup.user.dto.SigninRequest;
import eu.ensup.user.dto.SignupRequest;
import eu.ensup.user.dto.TokenResponse;

public interface AuthService {

    TokenResponse signin(SigninRequest signinRequest);
    void signup(SignupRequest signupRequest);
}