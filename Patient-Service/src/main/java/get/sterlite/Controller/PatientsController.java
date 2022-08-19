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

import get.sterlite.model.Patient;
import get.sterlite.model.PatientResponse;
import get.sterlite.service.PatientService;

@RestController
@RequestMapping("/api/v1/patients-ms")
public class PatientsController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${greetings}")
    private String greetings;
    
    @GetMapping("")
    public String index() {
        return greetings+" from "+appName;
    }

    @Autowired
    PatientService patientService;

    @GetMapping(value ="/patients", produces = "application/json")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(value ="/patients/{id}", produces = "application/json")
    public PatientResponse getPatientByID(@PathVariable("id") String id) {
        return patientService.getPatient(id);
    }

    @PutMapping(value ="/patients/{id}",consumes = "application/json", produces = "application/json")
    public PatientResponse updatePatient(@PathVariable("id") String id, @RequestBody Patient patient) {
        return patientService.updatePatientDetails(id, patient);
    }

    @DeleteMapping("/patients/{id}")
    public PatientResponse deletePatient(@PathVariable("id") String id) {
        return patientService.deletePatient(id);
    }
    
}
