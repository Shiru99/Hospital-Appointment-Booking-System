package get.sterlite.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Patients")
public class Patient {

    @Id
    @NotNull
    @Size(min = 10, max = 10, message = "Mobile no. must be 10 digits")
    @Column(name = "patient_id", unique = true, length = 10)
    private String mobileNum;

    @Transient
    @NotNull
    @Size(min = 1, message = "Password cannot be empty")
    private String password="****";

    @NotNull
    @Size(min = 1, max = 255, message = "fullName must be 1-255 characters long")
    @Column(name = "full_name")
    private String fullName;

    public Patient() {
    }

    public Patient(String mobileNum, String fullName, String password) {
        this.mobileNum = mobileNum;
        this.password = password;
        this.fullName = fullName;
    }

    public Patient(String mobileNum, String fullName) {
        this.mobileNum = mobileNum;
        this.fullName = fullName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Patient [fullName=" + fullName + ", mobileNum=" + mobileNum + ", password=" + password + "]";
    }

    

}
