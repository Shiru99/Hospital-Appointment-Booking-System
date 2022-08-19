package get.sterlite.Authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class LoginUser {

    @Id
    @Column(name = "mobile_num", unique = true, nullable = false)
    private String mobileNum;

    @Column(name = "password")
    private String password;

    public LoginUser(String mobileNum, String password) {
        this.setMobileNum(mobileNum);
        this.setPassword(password);
    }

    public LoginUser() {
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
