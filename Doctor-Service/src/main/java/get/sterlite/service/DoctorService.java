package get.sterlite.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.Authentication.service.UserService;
import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.Department;
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
    UserService userService;

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

    public DoctorResponse getDoctor(String doctorId) {
        Optional<Doctor> doctor = doctorRepository.findByMobileNum(doctorId);

        if (doctor.isPresent())
            return new DoctorResponse("success", doctor.get(), doctorDetailsService.getDoctorDetails(doctorId));
        else
            return new DoctorResponse("No doctor found with ID : " + doctorId, null);

    }

    public DoctorResponse updateDoctor(String doctorId, DoctorRequest doctorRequest)
            throws InvalidInputsException {
        if (!isDoctorExist(doctorId)) {
            return new DoctorResponse("No doctor found with ID : " + doctorId, null, null);
        } else {

            DoctorResponse doctorResponse = getDoctor(doctorId);

            if (doctorRequest.getDoctor() != null) {
                if (!doctorId.equals(doctorRequest.getDoctor().getMobileNum())) {
                    throw new InvalidInputsException("Incorrect mobile no. for Doctor ID : " + doctorId);
                }
                doctorRepository.save(doctorRequest.getDoctor());
                doctorResponse.setDoctor(doctorRequest.getDoctor());
            }

            if (doctorRequest.getDoctorDetails() != null) {
                if (!doctorId.equals(doctorRequest.getDoctorDetails().getMobileNum())) {
                    throw new InvalidInputsException("Incorrect mobile no. for Doctor ID : " + doctorId);
                }
                doctorDetailsService.saveDoctorDetails(doctorRequest.getDoctorDetails());
                doctorResponse.setDoctorDetails(doctorRequest.getDoctorDetails());
            }
            doctorResponse.setStatus("success");
            return doctorResponse;
        }
    }

    public DoctorResponse deleteDoctor(String doctorId) {
        Optional<Doctor> doctor = doctorRepository.findByMobileNum(doctorId);
        if (doctor.isPresent()) {

            userService.deleteUser(doctorId);

            DoctorDetails doctorDetails = doctorDetailsService.getDoctorDetails(doctorId);

            return new DoctorResponse("success", doctor.get(), doctorDetails);
        } else {
            return new DoctorResponse("No doctor found with ID : " + doctorId, null);
        }
    }

    public List<Doctor> getDoctorsBySearchQuery(String query) {

        Set<Doctor> doctors=new HashSet<>(); 

        doctors.addAll(findDoctorsByName(query));
        doctors.addAll(findDoctorsByDepartmentName(query));

        return new ArrayList<>(doctors);
    }

    private List<Doctor> findDoctorsByName(String query) {
        return doctorRepository.findByFullNameContaining(query);
    }

    private List<Doctor> findDoctorsByDepartmentName(String query) {
        List<Doctor> doctors=new ArrayList<>(); 

        for (Department d : Department.values()) {
            if(d.name().toLowerCase().contains(query.toLowerCase())) {
                doctors.addAll(doctorRepository.findByDepartment(d));
            }
        }
        return doctors;
    }

}