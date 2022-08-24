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
import get.sterlite.model.Department;
import get.sterlite.model.Doctor;
import get.sterlite.model.DoctorDetails;
import get.sterlite.model.DoctorRequest;
import get.sterlite.model.DoctorResponse;
import get.sterlite.repository.DoctorRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class DoctorServiceTest {

    private DoctorService doctorService;
    private DoctorRepository doctorRepository;
    private UserService userService;
    private DoctorDetailsService doctorDetailsService;
    
    private Doctor doctor;
    private DoctorDetails doctorDetails;

    @BeforeAll
    void setup() {
        doctorRepository = mock(DoctorRepository.class);
        userService = mock(UserService.class);
        doctorDetailsService = mock(DoctorDetailsService.class);

        doctorService = new DoctorService();

        doctorService.doctorRepository = doctorRepository;
        doctorService.userService = userService;
        doctorService.doctorDetailsService = doctorDetailsService;

        doctor = new Doctor("1234567899", "****", "Dr. Santosh Tharwani",
        Department.UNKNOWN, "MD (Anaesthesiology), PDCC (Pain & Palliative care)", "https://balcomedicalcentre.com/uploads/doctor/dr-santosh-tharwani.jpg");

        doctorDetails = new DoctorDetails("1234567899", "NA", "NA", "Medicine");
        
    }

    @Test
    @DisplayName("Save existing doctor")
    void testSaveDoctor() {
        String doctorId = doctor.getMobileNum();

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.of(doctor));

        Exception thrown = assertThrows(Exception.class, () -> doctorService.saveDoctor(doctor));

        assertEquals("Doctor already exist with Doctor ID: " + doctorId, thrown.getMessage());
    }

    @Test
    @DisplayName("Save new doctor")
    void testSaveDoctor1() {
        String doctorId = doctor.getMobileNum();

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.empty());

        when(doctorRepository.save(doctor)).thenReturn(doctor);
        Mockito.doNothing().when(doctorDetailsService).saveDoctorDetails(doctorId);
        
        doctorService.saveDoctor(doctor);
    }

    @Test
    @DisplayName("Deleting non-existing doctor")
    void testDeleteDoctor() {
        String doctorId = doctor.getMobileNum();

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.empty());

        DoctorResponse res = doctorService.deleteDoctor(doctorId);

        assertEquals("No doctor found with ID : " + doctorId, res.getStatus());
    }

    @Test
    @DisplayName("Deleting existing doctor")
    void testDeleteDoctor1() {

        String doctorId = doctor.getMobileNum();

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.of(doctor));

        Mockito.doNothing().when(userService).deleteUser(doctorId);

        DoctorResponse res = doctorService.deleteDoctor(doctorId);

        assertEquals("success", res.getStatus());
        assertEquals(doctor, res.getDoctor());
    }


    @Test
    @DisplayName("Get non-existing doctor")
    void testGetDoctor() {

        String doctorId = doctor.getMobileNum();

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.empty());

        DoctorResponse res = doctorService.getDoctor(doctorId);

        assertEquals("No doctor found with ID : " + doctorId, res.getStatus());
    }

    @Test
    @DisplayName("Get existing doctor")
    void testGetDoctor1() {

        String doctorId = doctor.getMobileNum();

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.of(doctor));

        when(doctorDetailsService.getDoctorDetails(doctorId)).thenReturn(doctorDetails);

        DoctorResponse res = doctorService.getDoctor(doctorId);

        assertEquals("success", res.getStatus());
        assertEquals(doctor, res.getDoctor());
        assertEquals(doctorDetails, res.getDoctorDetails());

    }

    @Test
    @DisplayName("Update non-existing doctor")
    void testUpdateDoctor() throws InvalidInputsException {
        String doctorId = doctor.getMobileNum();

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.empty());

        DoctorRequest doctorRequest = new DoctorRequest(doctor, doctorDetails);

        DoctorResponse res = doctorService.updateDoctor(doctorId,doctorRequest );

        assertEquals("No doctor found with ID : " + doctorId, res.getStatus());
    }

    @Test
    @DisplayName("Update doctor with invalid inputs")
    void testUpdateDoctor1() throws InvalidInputsException {

        String doctorId = "1234567888";

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.of(doctor));

        DoctorRequest doctorRequest = new DoctorRequest(doctor, doctorDetails);

        Exception thrown = assertThrows(InvalidInputsException.class,
                () -> doctorService.updateDoctor(doctorId, doctorRequest));

        assertEquals("Incorrect mobile no. for Doctor ID : " + doctorId, thrown.getMessage());
    }

    @Test
    @DisplayName("Update doctor with valid inputs")
    void testUpdateDoctor2() throws InvalidInputsException {

        String doctorId =  doctor.getMobileNum();

        when(doctorRepository.findByMobileNum(doctorId)).thenReturn(Optional.of(doctor));

        DoctorRequest doctorRequest = new DoctorRequest(doctor, doctorDetails);

        DoctorResponse res = doctorService.updateDoctor(doctorId, doctorRequest);

        assertEquals("success", res.getStatus());
        assertEquals(doctor, res.getDoctor());
        assertEquals(doctorDetails, res.getDoctorDetails());
    }

}
