package get.sterlite.Authentication.model;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

    private String mobileNum;
    private String password;

    public AuthenticationRequest(String mobileNum, String password) {
        this.setMobileNum(mobileNum);
        this.setPassword(password);
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String username) {
        this.mobileNum = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
