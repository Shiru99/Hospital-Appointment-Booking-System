package get.sterlite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.Authentication.service.UserService;
import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.Patient;
import get.sterlite.model.PatientRequest;
import get.sterlite.model.PatientResponse;
import get.sterlite.repository.PatientRepository;

@Service("patientService")
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserService userService;

    public void savePatient(Patient patient) {
        if (!isPatientExist(patient.getMobileNum())) {
            patientRepository.save(patient);
        } else {
            throw new RuntimeException(
                    "Patient already exist with Patient ID: " + patient.getMobileNum());
        }

    }

    public boolean isPatientExist(String patientId) {
        return patientRepository.findByMobileNum(patientId).isPresent();
    }

    public List<Patient> getAllPatients() {
        return (List<Patient>) patientRepository.findAll();
    }

    public PatientResponse getPatient(String patientId) {
        Optional<Patient> patient = patientRepository.findByMobileNum(patientId);

        if (patient.isPresent())
            return new PatientResponse("success", patient.get());
        else
            return new PatientResponse("No patient found with ID : " + patientId, null);

    }

    public PatientResponse updatePatientDetails(String patientId, PatientRequest patientRequest) throws InvalidInputsException {
        if (!isPatientExist(patientId)) {
            return new PatientResponse("No patient found with Mobile Number : " + patientId, null);
        } else {
            if (!patientId.equals(patientRequest.getPatient().getMobileNum())) {
                throw new InvalidInputsException("Incorrect mobile no. for Patient ID : " + patientId);
            }

            patientRepository.save(patientRequest.getPatient());
            return new PatientResponse("success", patientRequest.getPatient());
        }
    }

    public PatientResponse deletePatient(String patientId) {
        Optional<Patient> patient = patientRepository.findByMobileNum(patientId);
        if (patient.isPresent()) {
            userService.deleteUser(patientId);
            return new PatientResponse("success", patient.get());
        } else {
            return new PatientResponse("No patient found with ID : " + patientId, null);
        }
    }

}