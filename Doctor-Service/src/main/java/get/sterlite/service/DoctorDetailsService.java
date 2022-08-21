package get.sterlite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import get.sterlite.model.DoctorDetails;
import get.sterlite.repository.DoctorDetailsRepository;

@Transactional
@Service("DoctorDetailsService")
public class DoctorDetailsService {

    @Autowired
    DoctorDetailsRepository dDRepository;

    public void saveDoctorDetails(String mobileNum) {
        dDRepository.save(new DoctorDetails(mobileNum));
    }

    public DoctorDetails getDoctorDetails(String id) {
        return dDRepository.findByMobileNum(id).get();
    }

    public void saveDoctorDetails(DoctorDetails doctorDetails) {
        dDRepository.save(doctorDetails);
    }

}