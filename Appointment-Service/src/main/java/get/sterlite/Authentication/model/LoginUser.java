package get.sterlite.Authentication.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Users")
public class LoginUser implements Serializable {

    @Id 
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    @NotNull
    @Column(name = "mobile_num", unique = true, length = 10, nullable = false)
    private String mobileNum;

    @NotNull
    @Column(name = "password", nullable = false)
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
