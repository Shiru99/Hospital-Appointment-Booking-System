package get.sterlite.Authentication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import get.sterlite.Authentication.model.LoginUser;
import get.sterlite.Authentication.repository.LoginUserRepository;
import get.sterlite.model.Patient;

@Service("UserService")
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String pass = getUserPassword(username);
        return new User(username, pass, new ArrayList<>());
    }

    @Autowired
    LoginUserRepository loginUserRepository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String getUserPassword(String mobileNum) throws UsernameNotFoundException {

        LoginUser loginUser = loginUserRepository.findByMobileNum(mobileNum)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobileNum: " + mobileNum));

        return loginUser.getPassword();
    }

    public boolean isUserExist(String mobileNum) {
        return loginUserRepository.findByMobileNum(mobileNum).isPresent();
    }

    public void saveUser(Patient patient) {
        if (!isUserExist(patient.getMobileNum())) {
            LoginUser loginUser = new LoginUser(patient.getMobileNum(),
                    passwordEncoder().encode(patient.getPassword()));

            loginUserRepository.save(loginUser);
        } else {
            throw new RuntimeException("User already exist with mobileNum: " + patient.getMobileNum());
        }
    }

    public void deleteUser(String id) {
        LoginUser loginUser = loginUserRepository.findByMobileNum(id).get();
        loginUserRepository.delete(loginUser);
    }
}