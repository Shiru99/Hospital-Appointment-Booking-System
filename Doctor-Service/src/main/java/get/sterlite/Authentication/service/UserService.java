package get.sterlite.Authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import get.sterlite.Authentication.model.LoginUser;
import get.sterlite.Authentication.repository.LoginUserRepository;
import get.sterlite.model.Doctor;

@Service("userService")
public class UserService {

    @Autowired
    LoginUserRepository loginUserRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public String getUserPassword(String mobileNum) throws UsernameNotFoundException {

        LoginUser loginUser = loginUserRepository.findByMobileNum(mobileNum)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobileNum: " + mobileNum));

        return loginUser.getPassword();
    }

    public boolean isUserExist(String mobileNum) {
        return loginUserRepository.findByMobileNum(mobileNum).isPresent();
    }

    public void saveUser(Doctor doctor) {
        if (!isUserExist(doctor.getMobileNum())) {
            LoginUser loginUser = new LoginUser(doctor.getMobileNum(),
                    passwordEncoder.encode(doctor.getPassword()));

            loginUserRepository.save(loginUser);
        } else {
            throw new RuntimeException("User already exist with mobileNum: " + doctor.getMobileNum());
        }
    }
}