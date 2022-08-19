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
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import get.sterlite.Authentication.model.AuthenticationFailResponse;
import get.sterlite.Authentication.model.AuthenticationRequest;
import get.sterlite.Authentication.model.AuthenticationResponse;
import get.sterlite.Authentication.service.UserService;
import get.sterlite.Authentication.util.JwtUtil;

@TestInstance(Lifecycle.PER_CLASS)
public class AuthenticationControllerTest {
    
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
    @DisplayName("Testing Create JWT token with non-existing user")
    void testCreateAuthenticationToken1() {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("1234567800", "1234");

        when(userService.getUserPassword(authenticationRequest.getMobileNum())).thenThrow(new UsernameNotFoundException("User not found with mobileNum: " + authenticationRequest.getMobileNum()));

        ResponseEntity<?> response =  authenticationController.createAuthenticationToken(authenticationRequest);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("User not found with mobileNum: " + authenticationRequest.getMobileNum(), authenticationFailResponse.getMessage());
    }

    @Test
    @DisplayName("Testing Create JWT token with invalid credentials")
    void testCreateAuthenticationToken2() {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("1234567890", "1235");

        when(userService.getUserPassword(authenticationRequest.getMobileNum())).thenReturn("$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS");

        ResponseEntity<?> response =  authenticationController.createAuthenticationToken(authenticationRequest);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("Invalid Credentials", authenticationFailResponse.getMessage());
    }

    @Test
    @DisplayName("Testing Create JWT token with valid credentials")
    void testCreateAuthenticationToken3() throws Exception {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("1234567890", "1234");

        when(userService.getUserPassword(authenticationRequest.getMobileNum())).thenReturn("$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS");

        ResponseEntity<?> response =  authenticationController.createAuthenticationToken(authenticationRequest);

        assertEquals(200, response.getStatusCodeValue());
        
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) response.getBody();

        assertTrue(authenticationController.jwtTokenUtil.validateToken(authenticationResponse.getJwt()));
    }
}
