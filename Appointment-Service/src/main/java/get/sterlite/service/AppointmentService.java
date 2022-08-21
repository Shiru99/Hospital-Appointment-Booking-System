package get.sterlite.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.Appointment;
import get.sterlite.model.AppointmentRequest;
import get.sterlite.model.AppointmentResponse;
import get.sterlite.repository.AppointmentRepository;


@Transactional
@Service("AppointmentService")
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public void saveDoctor(Appointment appointment) {

    }

    public boolean isAppointmentExist(int appointmentId) {
        return appointmentRepository.findById(appointmentId).isPresent();
    }

    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public AppointmentResponse getAppointment(int id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if (appointment.isPresent())
            return new AppointmentResponse("success", appointment.get());
        else
            return new AppointmentResponse("No appointment found with ID : " + id, null);

    }

    public AppointmentResponse addAppointment(AppointmentRequest appointmentRequest) {
        appointmentRepository.save(appointmentRequest.getAppointment());
        return new AppointmentResponse("success", appointmentRequest.getAppointment());
    }

    public AppointmentResponse updateAppointment(int id, Appointment appointment) {

        Optional<Appointment> oldAppointment = appointmentRepository.findById(id);
        if (oldAppointment.isPresent()) {
            appointmentRepository.delete(oldAppointment.get());
            appointmentRepository.save(appointment);
            return new AppointmentResponse("success", appointment);
        } else{
            return new AppointmentResponse("No appointment found with ID : " + id, null);
        }
    }

    public AppointmentResponse deleteAppointment(int id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointmentRepository.delete(appointment.get());
            return new AppointmentResponse("success", appointment.get());
        } else
            return new AppointmentResponse("No appointment found with ID : " + id, null);
    }
}