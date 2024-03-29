package get.sterlite.Authentication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.validation.BindingResult;

import get.sterlite.Authentication.model.AuthenticationResponse;
import get.sterlite.Authentication.model.LoginUser;
import get.sterlite.Authentication.service.UserService;
import get.sterlite.Authentication.util.JwtUtil;
import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.Exception.LoginException;
import get.sterlite.service.DoctorService;

@TestInstance(Lifecycle.PER_CLASS)
public class AuthenticationLoginControllerTest {
    
    private AuthenticationController authenticationController;
    private UserService userService;
    private DoctorService doctorService;
    private BindingResult errors;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @BeforeAll
    void setup() {
        userService = mock(UserService.class);
        doctorService = mock(DoctorService.class);
        errors = mock(BindingResult.class);
       
        authenticationController = new AuthenticationController();
        authenticationController.userDetailsService = userService;
        authenticationController.doctorService = doctorService;
        authenticationController.passwordEncoder = passwordEncoder();
        authenticationController.jwtTokenUtil = new JwtUtil();

    }

    @Test
    @DisplayName("Create JWT token with invalid inputs for login")
    void testCreateAuthenticationToken1() {

        LoginUser loginUser = new LoginUser("1234567890", "");

        when(errors.hasErrors()).thenReturn(true);

        when(userService.getUserPassword(loginUser.getMobileNum())).thenReturn("$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS");

        assertThrows(InvalidInputsException.class, () -> authenticationController.loginAndCreateAuthenticationToken(loginUser, errors));

    }

    @Test
    @DisplayName("Create JWT token with non-existing user login")
    void testCreateAuthenticationToken2() throws Exception {

        LoginUser loginUser = new LoginUser("1234567800", "1234");

        when(errors.hasErrors()).thenReturn(false);

        when(userService.getUserPassword(loginUser.getMobileNum())).thenThrow(new UsernameNotFoundException("User not found with mobileNum: " + loginUser.getMobileNum()));

        Exception thrown =  assertThrows(UsernameNotFoundException.class, () -> authenticationController.loginAndCreateAuthenticationToken(loginUser, errors));

        assertEquals("User not found with mobileNum: " + loginUser.getMobileNum(), thrown.getMessage());
    
    }

    @Test
    @DisplayName("Create JWT token with invalid credentials login")
    void testCreateAuthenticationToken3() {

        LoginUser loginUser = new LoginUser("1234567890", "1235");

        when(errors.hasErrors()).thenReturn(false);

        when(userService.getUserPassword(loginUser.getMobileNum())).thenReturn("$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS");

        Exception thrown = assertThrows(LoginException.class, () -> authenticationController.loginAndCreateAuthenticationToken(loginUser, errors));

        assertEquals("Invalid Credentials", thrown.getMessage());

    }


    @Test
    @DisplayName("Create JWT token with invalid user login")
    void testCreateAuthenticationToken4() throws Exception {

        LoginUser loginUser = new LoginUser("1234567899", "1234");

        when(errors.hasErrors()).thenReturn(false);

        when(userService.getUserPassword(loginUser.getMobileNum())).thenReturn("$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS");

        when(doctorService.isDoctorExist(loginUser.getMobileNum())).thenReturn(false);

        Exception thrown = assertThrows(LoginException.class, () -> authenticationController.loginAndCreateAuthenticationToken(loginUser, errors));

        assertEquals("Doctor can't login as `Patient`", thrown.getMessage());

    }

    @Test
    @DisplayName("Create JWT token with valid credentials login")
    void testCreateAuthenticationToken5() throws Exception {

        LoginUser loginUser = new LoginUser("1234567890", "1234");

        when(errors.hasErrors()).thenReturn(false);

        when(userService.getUserPassword(loginUser.getMobileNum())).thenReturn("$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS");

        when(doctorService.isDoctorExist(loginUser.getMobileNum())).thenReturn(true);

        ResponseEntity<?> response =  authenticationController.loginAndCreateAuthenticationToken(loginUser, errors);

        assertEquals(200, response.getStatusCodeValue());
        
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) response.getBody();

        assertFalse(authenticationController.jwtTokenUtil.isTokenExpired(authenticationResponse.getJwt()));
    }
}
