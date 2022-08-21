package get.sterlite.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.Doctor;
import get.sterlite.model.DoctorDetails;
import get.sterlite.model.DoctorRequest;
import get.sterlite.model.DoctorResponse;
import get.sterlite.repository.DoctorRepository;

@Transactional
@Service("DoctorService")
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DoctorDetailsService doctorDetailsService;

    public void saveDoctor(Doctor doctor) {

        if (!isDoctorExist(doctor.getMobileNum())) {
            doctorRepository.save(doctor);
            doctorDetailsService.saveDoctorDetails(doctor.getMobileNum());
        } else {
            throw new RuntimeException(
                    "Doctor already exist with Doctor ID: " + doctor.getMobileNum());
        }

    }

    public boolean isDoctorExist(String doctorID) {
        return doctorRepository.findByMobileNum(doctorID).isPresent();
    }

    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public DoctorResponse getDoctor(String id) {
        Optional<Doctor> doctor = doctorRepository.findByMobileNum(id);

        if (doctor.isPresent())
            return new DoctorResponse("success", doctor.get(), doctorDetailsService.getDoctorDetails(id));
        else
            return new DoctorResponse("No Doctor found with ID : " + id, null);

    }

    public DoctorResponse updateDoctorDetails(String mobileNum, DoctorRequest doctorRequest)
            throws InvalidInputsException {
        if (!isDoctorExist(mobileNum)) {
            return new DoctorResponse("No Doctor found with ID : " + mobileNum, null, null);
        } else {

            DoctorResponse doctorResponse = getDoctor(mobileNum);

            if (doctorRequest.getDoctor() != null) {
                if (!mobileNum.equals(doctorRequest.getDoctor().getMobileNum())) {
                    throw new InvalidInputsException("Incorrect mobile no. for Doctor ID : " + mobileNum);
                }
                doctorRepository.save(doctorRequest.getDoctor());
                doctorResponse.setDoctor(doctorRequest.getDoctor());
            }

            if (doctorRequest.getDoctorDetails() != null) {
                if (!mobileNum.equals(doctorRequest.getDoctorDetails().getMobileNum())) {
                    throw new InvalidInputsException("Incorrect mobile no. for Doctor ID : " + mobileNum);
                }
                doctorDetailsService.saveDoctorDetails(doctorRequest.getDoctorDetails());
                doctorResponse.setDoctorDetails(doctorRequest.getDoctorDetails());
            }
            doctorResponse.setStatus("Success");
            return doctorResponse;
        }
    }

    public DoctorResponse deleteDoctor(String id) {
        Optional<Doctor> doctor = doctorRepository.findByMobileNum(id);
        if (doctor.isPresent()) {
            doctorRepository.deleteById(id);
            DoctorDetails doctorDetails = doctorDetailsService.getDoctorDetails(id);

            return new DoctorResponse("success", doctor.get(), doctorDetails);
        } else {
            return new DoctorResponse("No Doctor found with ID : " + id, null);
        }
    }

}