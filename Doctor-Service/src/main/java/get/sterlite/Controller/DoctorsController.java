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

    @GetMapping(value ="/doctors/{id}", produces = "application/json")
    public DoctorResponse getDoctorByID(@PathVariable("id") String id) {
        return doctorService.getDoctor(id);
    }

    @PutMapping(value ="/doctors/{id}",consumes = "application/json", produces = "application/json")
    public DoctorResponse updateDoctor(@PathVariable("id") String id, @RequestBody DoctorRequest doctorRequest) throws InvalidInputsException {
        return doctorService.updateDoctorDetails(id, doctorRequest);
    }

    @DeleteMapping("/doctors/{id}")
    public DoctorResponse deleteDoctor(@PathVariable("id") String id) {
        return doctorService.deleteDoctor(id);
    }
    
}
