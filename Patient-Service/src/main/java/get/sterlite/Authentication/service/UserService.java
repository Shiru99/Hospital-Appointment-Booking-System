package get.sterlite.Authentication.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    public String getUserPassword(String mobileNum) throws UsernameNotFoundException {
        return "$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS";
    }
}