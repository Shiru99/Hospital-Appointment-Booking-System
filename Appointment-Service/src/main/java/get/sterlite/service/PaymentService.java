package get.sterlite.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.model.Appointment;
import get.sterlite.model.AppointmentResponse;
import get.sterlite.model.Paid;
import get.sterlite.repository.AppointmentRepository;

@Transactional
@Service("PaymentService")
public class PaymentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public AppointmentResponse verifyPayment(@Valid int appointmentId) {

        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);

        if(appointment.isPresent()){
            appointment.get().setPaid(Paid.YES);
            appointmentRepository.save(appointment.get());
            return new AppointmentResponse("success", appointment.get());
        }
        else{
            return new AppointmentResponse("No appointment found with ID : " + appointmentId, null);
        }
    }
}
