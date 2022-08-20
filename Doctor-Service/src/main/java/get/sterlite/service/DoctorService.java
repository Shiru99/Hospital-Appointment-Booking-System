package get.sterlite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.model.Doctor;
import get.sterlite.model.DoctorResponse;
import get.sterlite.repository.DoctorRepository;

@Service("DoctorService")
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public void saveDoctor(Doctor doctor) {

        if (!isDoctorExist(doctor.getMobileNum())) {
            doctorRepository.save(doctor);
        } else {
            throw new RuntimeException(
                    "Doctor already exist with Doctor ID: " + doctor.getMobileNum());
        }

    }

    public boolean isDoctorExist(String DoctorID) {
        return doctorRepository.findByMobileNum(DoctorID).isPresent();
    }

    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public DoctorResponse getDoctor(String id) {
        Optional<Doctor> Doctor = doctorRepository.findByMobileNum(id);

        if (Doctor.isPresent())
            return new DoctorResponse("success", Doctor.get());
        else
            return new DoctorResponse("No Doctor found with ID : " + id, null);

    }

    public DoctorResponse updateDoctorDetails(String mobileNum, Doctor Doctor) {
        if (!isDoctorExist(mobileNum)) {
            return new DoctorResponse("No Doctor found with ID : " + mobileNum, null);
        } else {
            Doctor.setMobileNum(mobileNum);
            doctorRepository.save(Doctor);
            return new DoctorResponse("success", Doctor);
        }
    }

    public DoctorResponse deleteDoctor(String id) {
        Optional<Doctor> Doctor = doctorRepository.findByMobileNum(id);
        if (Doctor.isPresent()) {
            doctorRepository.deleteById(id);
            return new DoctorResponse("success", Doctor.get());
        } else {
            return new DoctorResponse("No Doctor found with ID : " + id, null);
        }
    }

}