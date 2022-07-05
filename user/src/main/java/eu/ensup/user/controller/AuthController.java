package eu.ensup.user.controller;

import eu.ensup.user.dto.SigninRequest;
import eu.ensup.user.dto.SignupRequest;
import eu.ensup.user.dto.TokenResponse;
import eu.ensup.user.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signup(@RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signin(@RequestBody SigninRequest signinRequest) {
        return new ResponseEntity<>(authService.signin(signinRequest), HttpStatus.OK);
    }


}