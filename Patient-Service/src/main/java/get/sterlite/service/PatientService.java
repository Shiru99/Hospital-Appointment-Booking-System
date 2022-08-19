package get.sterlite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.Authentication.model.SignupRequest;
import get.sterlite.model.Patient;
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

}