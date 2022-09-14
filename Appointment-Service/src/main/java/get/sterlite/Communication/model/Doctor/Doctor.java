package get.sterlite.Communication.model.Doctor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Doctors")
public class Doctor {

    @Id
    @NotNull(message = "Mobile No. cannot be null")
    @Size(min = 10, max = 10, message = "Mobile no. must be 10 digits")
    @Column(name = "doctor_id", unique = true, length = 10)
    private String mobileNum;

    @Transient
    @NotNull(message = "Password cannot be null")
    @Size(min = 1, message = "Password cannot be empty")
    private String password = "****";

    @NotNull(message = "Name cannot be empty")
    @Size(min = 1, max = 255, message = "fullName must be 1-255 characters long")
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Department must be specified")
    private Department department = Department.UNKNOWN;

    @NotNull(message = "Degree must be specified")
    @Size(min = 1, message = "Degree cannot be empty")
    @Column(name = "degree")
    private String degree;

    @NotNull(message = "profileURL must be specified")
    @Size(min = 1, message = "Degree cannot be empty")
    @Column(name = "profile_URL")
    private String profileURL;

    @Transient
    private DoctorDetails doctorDetails;

    public Doctor() {
    }

    public Doctor(String mobileNum, String password, String fullName,
            Department department, String degree, String profileURL) {
        this.mobileNum = mobileNum;
        this.password = password;
        this.fullName = fullName;
        this.department = department;
        this.degree = degree;
        this.profileURL = profileURL;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    @Override
    public String toString() {
        return "Doctor [mobileNum=" + mobileNum + ", password=" + password
                + ", fullName=" + fullName + ", department=" + department
                + ", degree=" + degree + ", profileURL=" + profileURL + "]";
    }

}
