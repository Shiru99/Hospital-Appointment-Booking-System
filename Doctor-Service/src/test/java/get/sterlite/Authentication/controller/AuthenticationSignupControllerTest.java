package get.sterlite.Authentication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import get.sterlite.Authentication.model.AuthenticationFailResponse;
import get.sterlite.Authentication.model.AuthenticationResponse;
import get.sterlite.Authentication.model.SignupRequest;
import get.sterlite.Authentication.service.UserService;
import get.sterlite.Authentication.util.JwtUtil;
import get.sterlite.service.DoctorService;

@TestInstance(Lifecycle.PER_CLASS)
public class AuthenticationSignupControllerTest {
    
    private AuthenticationController authenticationController;
    private UserService userService;
    private DoctorService doctorService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @BeforeAll
    void setup() {
        userService = mock(UserService.class);
        doctorService = mock(DoctorService.class);
       
        authenticationController = new AuthenticationController();
        authenticationController.userService = userService;
        authenticationController.doctorService = doctorService;
        authenticationController.passwordEncoder = passwordEncoder();
        authenticationController.jwtTokenUtil = new JwtUtil();
    }

    @Test
    @DisplayName("Create JWT token with invalid signup details")
    void testCreateAuthenticationToken1() {

        SignupRequest signupRequest = new SignupRequest("1234567889", "1234", "");

        ResponseEntity<?> response =  authenticationController.signupAndCreateAuthenticationToken(signupRequest);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("Full Name is required", authenticationFailResponse.getMessage());
    }

    @Test
    @DisplayName("Create JWT token with existing user signup")
    void testCreateAuthenticationToken2() {

        SignupRequest signupRequest = new SignupRequest("1234567890", "1234", "blah blah");

        Mockito.doThrow(new RuntimeException("User already exist with mobileNum: " + signupRequest.getMobileNum())).when(userService).saveUser(signupRequest);

        ResponseEntity<?> response =  authenticationController.signupAndCreateAuthenticationToken(signupRequest);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("User already exist with mobileNum: " + signupRequest.getMobileNum(), authenticationFailResponse.getMessage());
    }

    @Test
    @DisplayName("Create JWT token with existing user signup")
    void testCreateAuthenticationToken3() {

        SignupRequest signupRequest = new SignupRequest("1234567890", "1234", "blah blah");

        Mockito.doNothing().when(userService).saveUser(signupRequest);

        Mockito.doThrow(new RuntimeException("Doctor already exist with Doctor ID: " + signupRequest.getMobileNum())).when(doctorService).saveDoctor(signupRequest);
        

        ResponseEntity<?> response =  authenticationController.signupAndCreateAuthenticationToken(signupRequest);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("Doctor already exist with Doctor ID: " + signupRequest.getMobileNum(), authenticationFailResponse.getMessage());
    }

    @Test
    @DisplayName("Create JWT token with valid credentials")
    void testCreateAuthenticationToken4() throws Exception {

        SignupRequest signupRequest = new SignupRequest("1234567800", "1234", "blah blah");
        
        Mockito.doNothing().when(userService).saveUser(signupRequest);

        Mockito.doNothing().when(doctorService).saveDoctor(signupRequest);

        ResponseEntity<?> response =  authenticationController.signupAndCreateAuthenticationToken(signupRequest);

        assertEquals(200, response.getStatusCodeValue());
        
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) response.getBody();

        assertTrue(authenticationController.jwtTokenUtil.validateToken(authenticationResponse.getJwt()));
    }
}
