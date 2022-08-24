package get.sterlite.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.Doctor;
import get.sterlite.model.DoctorRequest;
import get.sterlite.model.DoctorResponse;
import get.sterlite.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctors-ms")
public class DoctorsController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${greetings}")
    private String greetings;
    
    @GetMapping("")
    public String index() {
        return greetings+" from "+appName;
    }

    @Autowired
    DoctorService doctorService;

    @GetMapping(value ="/doctors", produces = "application/json")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping(value ="/doctors/{doctorId}", produces = "application/json")
    public DoctorResponse getDoctorByID(@PathVariable("doctorId") String doctorId) {
        return doctorService.getDoctor(doctorId);
    }
    @GetMapping(value ="/doctors/search/{query}", produces = "application/json")
    public List<Doctor>  getDoctorsBySearchQuery(@PathVariable("query") String query) {
        return doctorService.getDoctorsBySearchQuery(query);
    }

    @PutMapping(value ="/doctors/{doctorId}",consumes = "application/json", produces = "application/json")
    public DoctorResponse updateDoctor(@PathVariable("doctorId") String doctorId, @RequestBody DoctorRequest doctorRequest) throws InvalidInputsException {
        return doctorService.updateDoctor(doctorId, doctorRequest);
    }

    @DeleteMapping("/doctors/{doctorId}")
    public DoctorResponse deleteDoctor(@PathVariable("doctorId") String doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }
    
}
