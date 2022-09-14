package get.sterlite.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import get.sterlite.model.Appointment;
import get.sterlite.model.AppointmentResponse;
import get.sterlite.model.Slot;
import get.sterlite.model.Status;
import get.sterlite.repository.AppointmentRepository;

@TestInstance(Lifecycle.PER_CLASS)
@SuppressWarnings("deprecation")
public class PaymentServiceTest {

    private AppointmentRepository appointmentRepository;
    private PaymentService paymentService;

    @BeforeAll
    void setup() {
        appointmentRepository = mock(AppointmentRepository.class);

        paymentService = new PaymentService();

        paymentService.appointmentRepository = appointmentRepository;
    }

    @Test
    @DisplayName("Pay for non-existing appointment")
    void testVerifyPayment() {

        int appointmentId = 0;
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());
        
        AppointmentResponse res =  paymentService.verifyPayment(appointmentId);

        assertEquals("No appointment found with ID : " + appointmentId, res.getStatus());
    }

    @Test
    @DisplayName("Pay for existing appointment")
    void testVerifyPayment2() {

        int appointmentId = 0;

        Appointment appointment = new Appointment(
                "1234567899", "123456789", new Date(2022, 11, 20), Slot.MORNING2, Status.ACTIVE,
                "Text");

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));
        
        AppointmentResponse res =  paymentService.verifyPayment(appointmentId);

        assertEquals("success", res.getStatus());
        assertEquals(appointment, res.getAppointment());
    }
}
