package get.sterlite.Authentication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import get.sterlite.Authentication.model.LoginUser;

@Repository
public interface LoginUserRepository extends CrudRepository<LoginUser,String> {
    Optional<LoginUser> findByMobileNum(String username);
}
