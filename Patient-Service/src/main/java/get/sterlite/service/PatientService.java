package get.sterlite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.Authentication.model.SignupRequest;
import get.sterlite.model.Patient;
import get.sterlite.model.PatientResponse;
import get.sterlite.repository.PatientRepository;

@Service("patientService")
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public void savePatient(SignupRequest signupRequest) {

        if (!isPatientExist(signupRequest.getMobileNum())) {
            Patient patient = new Patient(signupRequest.getMobileNum(),
                    signupRequest.getFullName());

            patientRepository.save(patient);
        } else {
            throw new RuntimeException(
                    "Patient already exist with Patient ID: " + signupRequest.getMobileNum());
        }

    }

    public boolean isPatientExist(String patientID) {
        return patientRepository.findByPatientID(patientID).isPresent();
    }

    public List<Patient> getAllPatients() {
        return (List<Patient>) patientRepository.findAll();
    }

    public PatientResponse getPatient(String id) {
        Optional<Patient> patient = patientRepository.findByPatientID(id);

        if (patient.isPresent())
            return new PatientResponse("success", patient.get());
        else
            return new PatientResponse("No patient found with ID : " + id, null);

    }

    public PatientResponse updatePatientDetails(String id, Patient patient) {
        if (!isPatientExist(id)) {
            return new PatientResponse("No patient found with ID : " + id, null);
        } else {
            patient.setPatientID(id);
            patientRepository.save(patient);
            return new PatientResponse("success", patient);
        }
    }

    public PatientResponse deletePatient(String id) {
        Optional<Patient> patient = patientRepository.findByPatientID(id);
        if (patient.isPresent()) {
            patientRepository.deleteById(id);
            return new PatientResponse("success", patient.get());
        } else {
            return new PatientResponse("No patient found with ID : " + id, null);
        }
    }

}