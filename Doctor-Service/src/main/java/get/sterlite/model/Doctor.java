package get.sterlite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Doctors")
public class Doctor {

    @Id
    @Column(name = "doctor_id", nullable = false)
    private String doctorID;

    @Column(name = "full_name")
    private String doctorName;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department = Department.UNKNOWN;

    @Column(name = "degree")
    private String degree;

    @Column(name = "profile_URL")
    private String profileURL;

    public Doctor() {
    }

    public Doctor(String doctorID, String doctorName) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
    }
    
    public Doctor(String doctorID, String doctorName, Department department, String degree, String profileURL) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.department = department;
        this.degree = degree;
        this.profileURL = profileURL;
    }

    @Override
    public String toString() {
        return "doctor [doctorID=" + doctorID + ", doctorName=" + doctorName + "]";
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

}
