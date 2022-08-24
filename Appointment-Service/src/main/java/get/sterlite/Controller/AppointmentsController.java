package get.sterlite.Controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.Appointment;
import get.sterlite.model.AppointmentRequest;
import get.sterlite.model.AppointmentResponse;
import get.sterlite.model.Slot;
import get.sterlite.model.Status;
import get.sterlite.service.AppointmentService;

@RestController
@RequestMapping("/api/v1/appointments-ms")
class AppointmentsController {
    @Value("${spring.application.name}")
    private String appName;

    @Value("${greetings}")
    private String greetings;

    @GetMapping("")
    public String index() {
        return greetings + " from " + appName;
    }

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(value = "/appointments", produces = "application/json")
    public List<Appointment> getAllAppointment() {
        return appointmentService.getAllAppointment();
    }

    @GetMapping(value = "/appointments/{id}", produces = "application/json")
    public AppointmentResponse getAppointment(@PathVariable("id") int id) {
        return appointmentService.getAppointment(id);
    }

    @PostMapping(value = "/appointments", produces = "application/json")
    public AppointmentResponse addAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest,
            BindingResult errors) throws InvalidInputsException {
        if (errors.hasErrors()) {
            throw new InvalidInputsException(errors);
        }
        return appointmentService.saveAppointment(appointmentRequest);
    }

    @PutMapping(value = "/appointments/{Id}", produces = "application/json")
    public AppointmentResponse updateAppointment(@PathVariable("Id") int Id,
            @RequestBody @Valid AppointmentRequest appointmentRequest, BindingResult errors)
            throws InvalidInputsException {
        if (errors.hasErrors()) {
            throw new InvalidInputsException(errors);
        }
        return appointmentService.updateAppointment(Id, appointmentRequest);
    }

    @DeleteMapping(value = "/appointments/{id}", produces = "application/json")
    public AppointmentResponse deleteAppointment(@PathVariable("id") int id) {
        return appointmentService.deleteAppointment(id);
    }

    @GetMapping(value = "/appointments/patients/{patientId}", produces = "application/json")
    public List<Appointment> getAppointmentsByPatient(@PathVariable("patientId") String patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @GetMapping(value = "/appointments/doctors/{doctorId}", produces = "application/json")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable("doctorId") String doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    @GetMapping(value = "/appointments/patients/{patientId}/{status}", produces = "application/json")
    public List<Appointment> getAppointmentsForPatientByStatus(@PathVariable("patientId") String patientId,
            @PathVariable("status") Status status) {
        return appointmentService.getAppointmentsForPatientByStatus(patientId, status);
    }

    @GetMapping(value = "/appointments/doctors/{doctorId}/{status}", produces = "application/json")
    public List<Appointment> getAppointmentsForDoctorByStatus(@PathVariable("doctorId") String doctorId, @PathVariable("status") Status status) {
        return appointmentService.getAppointmentsForDoctorByStatus(doctorId, status);
    }

    @GetMapping(value = "/appointments/free-slots/{doctorId}/{date}", produces = "application/json")
    public List<Slot> getFreeAppointmentSlots(@PathVariable("doctorId") String doctorId,
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return appointmentService.getFreeAppointmentSlots(doctorId, date);
    }
}
