package eu.ensup.user.service;

import eu.ensup.user.domain.User;
import eu.ensup.user.dto.SigninRequest;
import eu.ensup.user.dto.SignupRequest;
import eu.ensup.user.dto.TokenResponse;
import eu.ensup.user.repository.UserRepository;
import eu.ensup.user.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public TokenResponse signin(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return TokenResponse.builder().token(jwtUtil.generateToken(authentication)).build();
    }

    @Override
    public void signup(SignupRequest signupRequest) {
        userRepository.save(User.builder()
                .username(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword())).build());
    }
}