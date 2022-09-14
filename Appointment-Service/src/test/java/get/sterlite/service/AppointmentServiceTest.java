package get.sterlite.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import get.sterlite.model.Appointment;
import get.sterlite.model.AppointmentRequest;
import get.sterlite.model.AppointmentResponse;
import get.sterlite.model.Slot;
import get.sterlite.model.Status;
import get.sterlite.repository.AppointmentRepository;

@TestInstance(Lifecycle.PER_CLASS)
@SuppressWarnings("deprecation")
public class AppointmentServiceTest {

    private AppointmentService appointmentService;
    private AppointmentRepository appointmentRepository;

    private Appointment appointment;
    private AppointmentRequest appointmentRequest;

    @BeforeAll
    void setup() {
        appointmentRepository = mock(AppointmentRepository.class);

        appointmentService = new AppointmentService();
        appointmentService.appointmentRepository = appointmentRepository;

        appointment = new Appointment(
                "1234567899", "123456789", new Date(2022, 11, 20), Slot.MORNING2, Status.ACTIVE,
                "Medical Summary Reports provide an overview of the your personal history, occupational history, health history, psychiatric history, and functioning. These reports are often created by case workers. Ideally, they are also co-signed by the applicant’s doctor, psychologist, or psychiatrist.");
        
        appointmentRequest = new AppointmentRequest(appointment);
    }

    @Test
    @DisplayName("Verify if appointment is valid")
    void testIsValidAppointment() {

        when(
                appointmentRepository.findByDoctorIdAndPatientIdAndAppointmentDate(appointment.getDoctorId(),
                        appointment.getPatientId(), appointment.getAppointmentDate()))
                .thenReturn(Optional.of(appointment));

        AppointmentResponse res = appointmentService.isValidAppointment(appointmentRequest);

        assertEquals("Appointment already exists for doctor with ID : " + appointment.getDoctorId()
                + " and patient with ID : " + appointment.getPatientId() + " on " + appointment.getAppointmentDate()
                + " at " + appointment.getSlot(), res.getStatus());
    }

    @Test
    @DisplayName("Verify if appointment is valid")
    void testIsValidAppointment1() {

        when(appointmentRepository.findByDoctorIdAndPatientIdAndAppointmentDate(appointment.getDoctorId(),
                appointment.getPatientId(), appointment.getAppointmentDate()))
                .thenReturn(Optional.empty());

        when(appointmentRepository.findByDoctorIdAndAppointmentDateAndSlot(
                appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getSlot()))
                .thenReturn(Optional.of(appointment));

        AppointmentResponse res = appointmentService.isValidAppointment(appointmentRequest);

        assertEquals("Appointment already exists for doctor with ID : " + appointment.getDoctorId() + " on "
                + appointment.getAppointmentDate() + " at " + appointment.getSlot(), res.getStatus());
    }

    @Test
    @DisplayName("Verify if appointment is valid")
    void testIsValidAppointment2() {

        when(appointmentRepository.findByDoctorIdAndPatientIdAndAppointmentDate(appointment.getDoctorId(),
                appointment.getPatientId(), appointment.getAppointmentDate()))
                .thenReturn(Optional.empty());

        when(appointmentRepository.findByDoctorIdAndAppointmentDateAndSlot(
                appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getSlot()))
                .thenReturn(Optional.empty());

        when(appointmentRepository.findByPatientIdAndAppointmentDateAndSlot(appointment.getPatientId(),
                appointment.getAppointmentDate(), appointment.getSlot()))
                .thenReturn(Optional.of(appointment));

        AppointmentResponse res = appointmentService.isValidAppointment(appointmentRequest);

        assertEquals("Appointment already exists for patient with ID : " + appointment.getPatientId() + " on "
        + appointment.getAppointmentDate() + " at " + appointment.getSlot(), res.getStatus());
    }

    @Test
    @DisplayName("Verify if appointment is valid")
    void testIsValidAppointment3() {

        when(appointmentRepository.findByDoctorIdAndPatientIdAndAppointmentDate(appointment.getDoctorId(),
                appointment.getPatientId(), appointment.getAppointmentDate()))
                .thenReturn(Optional.empty());

        when(appointmentRepository.findByDoctorIdAndAppointmentDateAndSlot(
                appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getSlot()))
                .thenReturn(Optional.empty());

        when(appointmentRepository.findByPatientIdAndAppointmentDateAndSlot(appointment.getPatientId(),
                appointment.getAppointmentDate(), appointment.getSlot()))
                .thenReturn(Optional.empty());

        AppointmentResponse res = appointmentService.isValidAppointment(appointmentRequest);

        assertEquals(null, res);
    }

    @Test
    @DisplayName("Save existing appointment")
    void testSaveAppointment() {
        AppointmentResponse res =  appointmentService.saveAppointment(appointmentRequest);

        assertEquals("success", res.getStatus());
        assertEquals(appointment, res.getAppointment());
    }

    @Test
    @DisplayName("Delete non-existing appointment")
    void testDeleteAppointment() {
        int id = 0;
        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());

        AppointmentResponse res =  appointmentService.deleteAppointment(id);

        assertEquals("No appointment found with ID : " + id, res.getStatus());
    }

    @Test
    @DisplayName("Delete existing appointment")
    void testDeleteAppointment1() {
        int id = 0;
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));

        AppointmentResponse res =  appointmentService.deleteAppointment(id);

        assertEquals("success", res.getStatus());
        assertEquals(appointment, res.getAppointment());
    }


    @Test
    @DisplayName("Get non-existing appointment")
    void testGetAppointment() {
        int id = 0;
        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());

        AppointmentResponse res =  appointmentService.getAppointment(id);

        assertEquals("No appointment found with ID : " + id, res.getStatus());
    }

    @Test
    @DisplayName("Get existing appointment")
    void testGetAppointment1() {
        int id = 0;
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));

        AppointmentResponse res =  appointmentService.getAppointment(id);

        assertEquals("success", res.getStatus());
        assertEquals(appointment, res.getAppointment());
    }


    @Test
    void testGetFreeAppointmentSlots() {
        String doctorId = "1234567899";
        Date date = new Date(2022, 11, 20);
        when(appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date)).thenReturn(Arrays.asList(appointment));
        
        List<Slot> slots = appointmentService.getFreeAppointmentSlots(doctorId, date);

        assertEquals(8, slots.size());
    }

    @Test
    @DisplayName("Update non-existing appointment")
    void testUpdateAppointment() {
        int id = 0;

        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());

        AppointmentResponse res = appointmentService.updateAppointment(id, appointmentRequest);

        assertEquals("No appointment found with ID : " + id, res.getStatus());
    }

    @Test
    @DisplayName("Update existing appointment")
    void testUpdateAppointment1() {
        int id = 0;

        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));

        AppointmentResponse res = appointmentService.updateAppointment(id, appointmentRequest);

        assertEquals("success", res.getStatus());
        assertEquals(appointment, res.getAppointment());
    }

}
