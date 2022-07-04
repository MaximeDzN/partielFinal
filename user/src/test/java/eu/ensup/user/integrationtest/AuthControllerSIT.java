package eu.ensup.user.integrationtest;

import eu.ensup.user.controller.AuthController;
import eu.ensup.user.domain.User;
import eu.ensup.user.domain.enums.Role;
import eu.ensup.user.dto.SigninRequest;
import eu.ensup.user.repository.UserRepository;
import eu.ensup.user.security.JwtUtil;
import eu.ensup.user.service.AuthService;
import eu.ensup.user.service.UserService;
import eu.ensup.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;

import java.sql.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {AuthController.class})
public class AuthControllerSIT
{
    @Inject
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private AuthController authController;

    @Test
    public void testSignIn() throws Exception {
        // GIVEN
        SigninRequest signinRequest = new SigninRequest("user","password");
        String token = "token";

        when(authService.signin(signinRequest)).thenReturn(token);

        // WHEN
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                                .content(signinRequest.toString())
                                .contentType(MediaType.ALL)
                                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        // THEN
        assertThat(result.getResponse().getContentAsString(), equalTo(token));

        verify(authService).signin(signinRequest);
    }

    /*@Test
    public void testsignup()
    {
        User user = new User(1l, Role.ROLE_USER, "username", "password", "email", Date.valueOf("10/03/1999"), Date.valueOf("10/03/2000"));
        when(userRepository.save(user)).thenReturn(user);

        mockMvc.perform(post("/calculator").content()).andExpect(status().is2xxSuccessful()).
                andExpect(content().string(containsString("id=\"solution\""))).
                andExpect(content().string(containsString(">5</span>")));

        verify(userRepository).save(user);
    }*/
}
