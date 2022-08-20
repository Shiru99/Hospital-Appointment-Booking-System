package get.sterlite.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import get.sterlite.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,String> {
    Optional<Doctor>  findByMobileNum(String mobileNum);
    Optional<Doctor> findByFullName(String fullName);
}
