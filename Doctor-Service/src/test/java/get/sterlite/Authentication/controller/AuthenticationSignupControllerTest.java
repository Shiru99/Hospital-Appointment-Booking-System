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
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;

import get.sterlite.Authentication.model.AuthenticationFailResponse;
import get.sterlite.Authentication.model.AuthenticationResponse;
import get.sterlite.Authentication.service.UserService;
import get.sterlite.Authentication.util.JwtUtil;
import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.Department;
import get.sterlite.model.Doctor;
import get.sterlite.model.DoctorSignupRequest;
import get.sterlite.service.DoctorService;

@TestInstance(Lifecycle.PER_CLASS)
public class AuthenticationSignupControllerTest {
    
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
    @DisplayName("Create JWT token with invalid signup details")
    void testCreateAuthenticationToken1() throws Exception {

        Doctor doctor = new Doctor("1234567889", "1234", "Dr. x", Department.RADIOLOGY, "I.T.U.S.", "");

        DoctorSignupRequest doctorRequest = new DoctorSignupRequest();
        doctorRequest.setDoctor(doctor);
        

        when(errors.hasErrors()).thenReturn(true);

        assertThrows(InvalidInputsException.class, () -> authenticationController.signupAndCreateAuthenticationToken(doctorRequest, errors));
        
    }

    @Test
    @DisplayName("Create JWT token with existing user signup")
    void testCreateAuthenticationToken2() throws Exception {

        Doctor doctor = new Doctor("1234567899", "1234", "Dr. x", Department.RADIOLOGY, "I.T.U.S.", "google.com");

        DoctorSignupRequest doctorRequest = new DoctorSignupRequest();
        doctorRequest.setDoctor(doctor);

        when(errors.hasErrors()).thenReturn(false);

        Mockito.doThrow(new RuntimeException("User already exist with mobileNum: " + doctor.getMobileNum())).when(userService).saveUser(doctor);

        ResponseEntity<?> response =  authenticationController.signupAndCreateAuthenticationToken(doctorRequest, errors);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("User already exist with mobileNum: " + doctor.getMobileNum(), authenticationFailResponse.getMessage());
    }

    
    @Test
    @DisplayName("Create JWT token with valid credentials")
    void testCreateAuthenticationToken4() throws Exception {

        Doctor doctor = new Doctor("1234567888", "1234", "Dr. x", Department.RADIOLOGY, "I.T.U.S.", "google.com");

        DoctorSignupRequest doctorRequest = new DoctorSignupRequest();
        doctorRequest.setDoctor(doctor);

        when(errors.hasErrors()).thenReturn(false);
        
        Mockito.doNothing().when(userService).saveUser(doctor);

        Mockito.doNothing().when(doctorService).saveDoctor(doctor);

        ResponseEntity<?> response =  authenticationController.signupAndCreateAuthenticationToken(doctorRequest, errors);

        assertEquals(200, response.getStatusCodeValue());
        
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) response.getBody();

        assertFalse(authenticationController.jwtTokenUtil.isTokenExpired(authenticationResponse.getJwt()));
    }
}
