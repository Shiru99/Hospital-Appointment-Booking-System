package get.sterlite.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import get.sterlite.Authentication.service.UserService;
import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.Patient;
import get.sterlite.model.PatientRequest;
import get.sterlite.model.PatientResponse;
import get.sterlite.repository.PatientRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class PatientServiceTest {

    private PatientService patientService;
    private PatientRepository patientRepository;
    private UserService userService;
    private Patient patient;

    @BeforeAll
    void setup() {
        patientRepository = mock(PatientRepository.class);
        userService = mock(UserService.class);

        patientService = new PatientService();

        patientService.patientRepository = patientRepository;
        patientService.userService = userService;
        patient = new Patient("1234567890", "1234", "Patient X");
    }

    @Test
    @DisplayName("Save existing patient")
    void testSavePatient() {

        String patientId = patient.getMobileNum();

        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.of(patient));

        Exception thrown = assertThrows(Exception.class, () -> patientService.savePatient(patient));

        assertEquals("Patient already exist with Patient ID: " + patientId, thrown.getMessage());

    }

    @Test
    @DisplayName("Save new patient")
    void testSavePatient1() {

        String patientId = patient.getMobileNum();

        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.empty());
        patientService.savePatient(patient);
    }

    @Test
    @DisplayName("Delete non-existing patient")
    void testDeletePatient() {

        String patientId = patient.getMobileNum();

        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.empty());

        PatientResponse res = patientService.deletePatient(patientId);

        assertEquals("No patient found with ID : " + patientId, res.getStatus());
    }

    @Test
    @DisplayName("Delete existing patient")
    void testDeletePatient1() {

        String patientId = patient.getMobileNum();

        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.of(patient));

        Mockito.doNothing().when(userService).deleteUser(patientId);

        PatientResponse res = patientService.deletePatient(patientId);

        assertEquals("success", res.getStatus());
        assertEquals(patient, res.getPatient());
    }

    @Test
    @DisplayName("Get non-existing patient")
    void testGetPatient() {

        String patientId = patient.getMobileNum();

        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.empty());

        PatientResponse res = patientService.getPatient(patientId);

        assertEquals("No patient found with ID : " + patientId, res.getStatus());
    }

    @Test
    @DisplayName("Get existing patient")
    void testGetPatient1() {
        String patientId = patient.getMobileNum();

        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.of(patient));

        PatientResponse res = patientService.getPatient(patientId);

        assertEquals("success", res.getStatus());
        assertEquals(patient, res.getPatient());
    }

    @Test
    @DisplayName("Update non-existing patient")
    void testUpdatePatientDetails() throws InvalidInputsException {

        String patientId = patient.getMobileNum();

        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.empty());
        PatientRequest patientRequest = new PatientRequest(patient);
        PatientResponse res = patientService.updatePatientDetails(patientId, patientRequest);

        assertEquals("No patient found with Mobile Number : " + patientId, res.getStatus());
        assertEquals(null, res.getPatient());
    }

    @Test
    @DisplayName("Update patient with invalid inputs")
    void testUpdatePatientDetails1() throws InvalidInputsException {

        String patientId = "1234567888";

        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.of(patient));

        PatientRequest patientRequest = new PatientRequest(patient);

        Exception thrown = assertThrows(InvalidInputsException.class,
                () -> patientService.updatePatientDetails(patientId, patientRequest));

        assertEquals("Incorrect mobile no. for Patient ID : " + patientId, thrown.getMessage());
    }

    @Test
    @DisplayName("Update patient with valid inputs")
    void testUpdatePatientDetails2() throws InvalidInputsException {
        String patientId = patient.getMobileNum();

        when(patientRepository.findByMobileNum(patientId)).thenReturn(Optional.of(patient));

        when(patientRepository.save(patient)).thenReturn(patient);

        PatientRequest patientRequest = new PatientRequest(patient);

        PatientResponse res = patientService.updatePatientDetails(patientId, patientRequest);

        assertEquals("success", res.getStatus());
        assertEquals(patient, res.getPatient());
    }
}
