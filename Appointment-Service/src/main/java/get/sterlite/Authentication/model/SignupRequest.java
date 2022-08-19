package get.sterlite.Authentication.model;

import java.io.Serializable;

public class SignupRequest implements Serializable {

    private String mobileNum;
    private String password;
    private String fullName;

    public SignupRequest(String mobileNum, String password, String fullName) {
        this.setMobileNum(mobileNum);
        this.setPassword(password);
        this.setFullName(fullName);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

}
