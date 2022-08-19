package get.sterlite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.Authentication.model.SignupRequest;
import get.sterlite.model.Doctor;
import get.sterlite.model.DoctorResponse;
import get.sterlite.repository.DoctorRepository;

@Service("DoctorService")
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public void saveDoctor(SignupRequest signupRequest) {

        if (!isDoctorExist(signupRequest.getMobileNum())) {
            Doctor Doctor = new Doctor(signupRequest.getMobileNum(),
                    signupRequest.getFullName());

            doctorRepository.save(Doctor);
        } else {
            throw new RuntimeException(
                    "Doctor already exist with Doctor ID: " + signupRequest.getMobileNum());
        }

    }

    public boolean isDoctorExist(String DoctorID) {
        return doctorRepository.findByDoctorID(DoctorID).isPresent();
    }

    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public DoctorResponse getDoctor(String id) {
        Optional<Doctor> Doctor = doctorRepository.findByDoctorID(id);

        if (Doctor.isPresent())
            return new DoctorResponse("success", Doctor.get());
        else
            return new DoctorResponse("No Doctor found with ID : " + id, null);

    }

    public DoctorResponse updateDoctorDetails(String id, Doctor Doctor) {
        if (!isDoctorExist(id)) {
            return new DoctorResponse("No Doctor found with ID : " + id, null);
        } else {
            Doctor.setDoctorID(id);
            doctorRepository.save(Doctor);
            return new DoctorResponse("success", Doctor);
        }
    }

    public DoctorResponse deleteDoctor(String id) {
        Optional<Doctor> Doctor = doctorRepository.findByDoctorID(id);
        if (Doctor.isPresent()) {
            doctorRepository.deleteById(id);
            return new DoctorResponse("success", Doctor.get());
        } else {
            return new DoctorResponse("No Doctor found with ID : " + id, null);
        }
    }

}