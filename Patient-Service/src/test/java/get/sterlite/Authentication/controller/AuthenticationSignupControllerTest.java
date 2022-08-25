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
import get.sterlite.model.Patient;
import get.sterlite.model.PatientRequest;
import get.sterlite.service.PatientService;

@TestInstance(Lifecycle.PER_CLASS)
public class AuthenticationSignupControllerTest {
    
    private AuthenticationController authenticationController;
    private UserService userService;
    private PatientService patientService;
    private BindingResult errors;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @BeforeAll
    void setup() {
        userService = mock(UserService.class);
        patientService = mock(PatientService.class);
        errors = mock(BindingResult.class);
        
        authenticationController = new AuthenticationController();
        authenticationController.userDetailsService = userService;
        authenticationController.patientService = patientService;
        authenticationController.passwordEncoder = passwordEncoder();
        authenticationController.jwtTokenUtil = new JwtUtil();
    }

    @Test
    @DisplayName("Create JWT token with invalid inputs for signup")
    void testCreateAuthenticationToken4() throws Exception {

        Patient patient = new Patient("1234567888", "", "1234");

        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setPatient(patient);

        when(errors.hasErrors()).thenReturn(true);

        assertThrows(InvalidInputsException.class, () -> authenticationController.signupAndCreateAuthenticationToken(patientRequest, errors));
    }

    @Test
    @DisplayName("Create JWT token with existing user signup")
    void testCreateAuthenticationToken5() throws Exception {

        Patient patient = new Patient("1234567890", "blah blah", "1234");

        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setPatient(patient);

        when(errors.hasErrors()).thenReturn(false);

        Mockito.doThrow(new RuntimeException("User already exist with mobileNum: " + patient.getMobileNum())).when(userService).saveUser(patient);


        ResponseEntity<?> response =  authenticationController.signupAndCreateAuthenticationToken(patientRequest, errors);

        assertEquals(401, response.getStatusCodeValue());
        
        AuthenticationFailResponse authenticationFailResponse = (AuthenticationFailResponse) response.getBody();

        assertEquals("User already exist with mobileNum: " + patient.getMobileNum(), authenticationFailResponse.getMessage());
    }

    @Test
    @DisplayName("Create JWT token with valid credentials")
    void testCreateAuthenticationToken7() throws Exception {

        Patient patient = new Patient("1234567800", "1234", "blah blah");

        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setPatient(patient);

        when(errors.hasErrors()).thenReturn(false);
        
        Mockito.doNothing().when(userService).saveUser(patient);

        Mockito.doNothing().when(patientService).savePatient(patient);

        ResponseEntity<?> response =  authenticationController.signupAndCreateAuthenticationToken(patientRequest,errors);

        assertEquals(200, response.getStatusCodeValue());
        
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) response.getBody();

        assertFalse(authenticationController.jwtTokenUtil.isTokenExpired(authenticationResponse.getJwt()));
    }
}
