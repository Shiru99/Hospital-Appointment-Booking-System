package get.sterlite.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        return greetings+" from "+appName;
    }

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(value ="/appointments", produces = "application/json")
    public List<Appointment> getAllAppointment() {
        return appointmentService.getAllAppointment();
    }

    @GetMapping(value ="/appointments/{id}", produces = "application/json")
    public AppointmentResponse getAppointment(@PathVariable("id") int id) {
        return appointmentService.getAppointment(id);
    }

    @PostMapping(value ="/appointments", produces = "application/json")
    public AppointmentResponse addAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest, BindingResult errors) throws InvalidInputsException{
        if (errors.hasErrors()) {
			throw new InvalidInputsException(errors);
		}
        return appointmentService.addAppointment(appointmentRequest);
    }

    @PutMapping(value ="/appointments/{id}", produces = "application/json")
    public AppointmentResponse updateAppointment(@PathVariable("id") int id, @RequestBody @Valid AppointmentRequest appointmentRequest, BindingResult errors) throws InvalidInputsException{
        if (errors.hasErrors()) {
            throw new InvalidInputsException(errors);
        }
        return appointmentService.updateAppointment(id, appointmentRequest.getAppointment());
    }

    @DeleteMapping(value ="/appointments/{id}", produces = "application/json")
    public AppointmentResponse deleteAppointment(@PathVariable("id") int id) {
        return appointmentService.deleteAppointment(id);
    }
}
