package get.sterlite.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import get.sterlite.model.Appointment;
import get.sterlite.model.Slot;
import get.sterlite.model.Status;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer> {
    Optional<Appointment> findById(int id);
    List<Appointment> findAll();
    List<Appointment> findByDoctorId(String doctorId);
    List<Appointment> findByPatientId(String patientId);
    
    Optional<Appointment> findByPatientIdAndAppointmentDateAndSlot(String patientId, Date appointmentDate, Slot slot);
    Optional<Appointment> findByDoctorIdAndAppointmentDateAndSlot(String doctorId, Date appointmentDate, Slot slot);
    Optional<Appointment> findByDoctorIdAndPatientIdAndAppointmentDate(String doctorId, String patientId, Date appointmentDate);

    List<Appointment> findByDoctorIdAndPatientId(String doctorId, String patientId);
    List<Appointment> findByDoctorIdAndStatus(String doctorId, Status status);
    List<Appointment> findByDoctorIdAndAppointmentDate(String doctorId, Date date);
}
