package get.sterlite.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public AppointmentResponse isValidAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentRequest.getAppointment();

        Optional<Appointment> existingAppointment;

        existingAppointment = appointmentRepository.findByDoctorIdAndPatientIdAndAppointmentDate(appointment.getDoctorId(), appointment.getPatientId(), appointment.getAppointmentDate());

        if (existingAppointment.isPresent()) {
            return new AppointmentResponse(
                    "Appointment already exists for doctor with ID : " + appointment.getDoctorId() + " and patient with ID : " + appointment.getPatientId() + " on " + appointment.getAppointmentDate() + " at " + existingAppointment.get().getSlot(), null);
        } 

        existingAppointment = appointmentRepository.findByDoctorIdAndAppointmentDateAndSlot(
            appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getSlot());

        if (existingAppointment.isPresent()) {
            return new AppointmentResponse(
                    "Appointment already exists for doctor with ID : " + appointment.getDoctorId() + " on "
                            + appointment.getAppointmentDate() + " at " + appointment.getSlot(),
                    null);
        }
        existingAppointment = appointmentRepository.findByPatientIdAndAppointmentDateAndSlot(appointment.getPatientId(),
                appointment.getAppointmentDate(), appointment.getSlot());
        if (existingAppointment.isPresent()) {
            return new AppointmentResponse(
                    "Appointment already exists for patient with ID : " + appointment.getPatientId() + " on "
                            + appointment.getAppointmentDate() + " at " + appointment.getSlot(),
                    null);
        }
        return null;
    }
    
    public AppointmentResponse addAppointment(AppointmentRequest appointmentRequest) {
        AppointmentResponse appointmentResponse = isValidAppointment(appointmentRequest);

        if (appointmentResponse != null) {
            return appointmentResponse;
        }

        appointmentRepository.save(appointmentRequest.getAppointment());
        return new AppointmentResponse("success", appointmentRequest.getAppointment());
    }

    public AppointmentResponse deleteAppointment(int id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointmentRepository.delete(appointment.get());
            return new AppointmentResponse("success", appointment.get());
        } else
            return new AppointmentResponse("No appointment found with ID : " + id, null);
    }

    public AppointmentResponse updateAppointment(int id, @Valid AppointmentRequest appointmentRequest) {

        AppointmentResponse appointmentResponse = isValidAppointment(appointmentRequest);

        if (appointmentResponse != null) {
            return appointmentResponse;
        }

        Optional<Appointment> oldAppointmentOptional = appointmentRepository.findById(id);

        if (oldAppointmentOptional.isPresent()) {
            Appointment oldAppointment = oldAppointmentOptional.get();

            oldAppointment.setAppointmentDate(appointmentRequest.getAppointment().getAppointmentDate());
            oldAppointment.setSlot(appointmentRequest.getAppointment().getSlot());
            oldAppointment.setDoctorId(appointmentRequest.getAppointment().getDoctorId());
            oldAppointment.setPatientId(appointmentRequest.getAppointment().getPatientId());
            oldAppointment.setStatus(appointmentRequest.getAppointment().getStatus());
            oldAppointment.setMessage(appointmentRequest.getAppointment().getMessage());
            oldAppointment.setStatus(appointmentRequest.getAppointment().getStatus());

            appointmentRepository.save(oldAppointment);

            return new AppointmentResponse("success", oldAppointment);
        } else {
            return new AppointmentResponse("No appointment found with ID : " + id, null);
        }
    }
    
}