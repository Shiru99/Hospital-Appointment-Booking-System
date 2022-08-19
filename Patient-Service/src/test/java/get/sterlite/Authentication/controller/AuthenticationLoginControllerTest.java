package get.sterlite.Authentication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import get.sterlite.Authentication.model.AuthenticationFailResponse;
import get.sterlite.Authentication.model.AuthenticationResponse;
import get.sterlite.Authentication.model.LoginRequest;
import get.sterlite.Authentication.model.SignupRequest;
import get.sterlite.Authentication.service.UserService;
import get.sterlite.Authentication.util.JwtUtil;
import get.sterlite.service.PatientService;

@TestInstance(Lifecycle.PER_CLASS)
public class AuthenticationLoginControllerTest {
    
    private AuthenticationController authenticationController;
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @BeforeAll
    void setup() {
        userService = mock(UserService.class);
       
        authenticationController = new AuthenticationController();
        authenticationController.userService = userService;
        authenticationController.passwordEncoder = passwordEncoder();
        authenticationController.jwtTokenUtil = new JwtUtil();
    }

    @Test
    @DisplayName("Create JWT token with non-existing user login")
    void testCreateAuthenticationToken1() {

        LoginRequest loginRequest = new LoginRequest("1234567800", "1234");

        when(userService.getUserPassword(loginRequest.getMobileNum())).thenThrow(new UsernameNotFoundException("User not found with mobileNum: " + loginRequest.getMobileNum()));

        ResponseEntity<?> response =  authenticationController.loginAndCreateAuthenticationToken(loginRequest);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("User not found with mobileNum: " + loginRequest.getMobileNum(), authenticationFailResponse.getMessage());
    }

    @Test
    @DisplayName("Create JWT token with invalid credentials login")
    void testCreateAuthenticationToken2() {

        LoginRequest loginRequest = new LoginRequest("1234567890", "1235");

        when(userService.getUserPassword(loginRequest.getMobileNum())).thenReturn("$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS");

        ResponseEntity<?> response =  authenticationController.loginAndCreateAuthenticationToken(loginRequest);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("Invalid Credentials", authenticationFailResponse.getMessage());
    }

    @Test
    @DisplayName("Create JWT token with valid credentials login")
    void testCreateAuthenticationToken3() throws Exception {

        LoginRequest loginRequest = new LoginRequest("1234567890", "1234");

        when(userService.getUserPassword(loginRequest.getMobileNum())).thenReturn("$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS");

        ResponseEntity<?> response =  authenticationController.loginAndCreateAuthenticationToken(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
        
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) response.getBody();

        assertTrue(authenticationController.jwtTokenUtil.validateToken(authenticationResponse.getJwt()));
    }
}
