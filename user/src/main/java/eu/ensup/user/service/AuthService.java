package eu.ensup.user.service;

import eu.ensup.user.dto.SigninRequest;
import eu.ensup.user.dto.SignupRequest;

public interface AuthService {

    String signin(SigninRequest signinRequest);
    void signup(SignupRequest signupRequest);
}