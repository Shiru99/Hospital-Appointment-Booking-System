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

    public boolean isPatientExist(String patientID) {
        return patientRepository.findByMobileNum(patientID).isPresent();
    }

    public List<Patient> getAllPatients() {
        return (List<Patient>) patientRepository.findAll();
    }

    public PatientResponse getPatient(String id) {
        Optional<Patient> patient = patientRepository.findByMobileNum(id);

        if (patient.isPresent())
            return new PatientResponse("success", patient.get());
        else
            return new PatientResponse("No patient found with ID : " + id, null);

    }

    public PatientResponse updatePatientDetails(String mobileNum, PatientRequest patientRequest) throws InvalidInputsException {
        if (!isPatientExist(mobileNum)) {
            return new PatientResponse("No patient found with Mobile Number : " + mobileNum, null);
        } else {
            if (!mobileNum.equals(patientRequest.getPatient().getMobileNum())) {
                throw new InvalidInputsException("Incorrect mobile no. for Patient ID : " + mobileNum);
            }

            patientRepository.save(patientRequest.getPatient());
            return new PatientResponse("success", patientRequest.getPatient());
        }
    }

    public PatientResponse deletePatient(String id) {
        Optional<Patient> patient = patientRepository.findByMobileNum(id);
        if (patient.isPresent()) {
            userService.deleteUser(id);
            return new PatientResponse("success", patient.get());
        } else {
            return new PatientResponse("No patient found with ID : " + id, null);
        }
    }

}