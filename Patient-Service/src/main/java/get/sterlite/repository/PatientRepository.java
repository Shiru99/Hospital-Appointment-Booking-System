package get.sterlite.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import get.sterlite.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient,String> {
    Optional<Patient> findByMobileNum(String mobileNum);
    Optional<Patient> findByFullName(String fullName);
}
