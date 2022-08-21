package get.sterlite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DoctorDetails")
public class DoctorDetails {
    
    @Id
    @NotNull(message = "Mobile No. cannot be null")
    @Size(min = 10, max = 10, message = "Mobile no. must be 10 digits")
    @Column(name = "doctor_id", unique = true, length = 10)
    private String mobileNum;

    @NotNull(message = "Education cannot be empty")
    @Size(max = 1000, message = "Education must be less than 1000 characters")
    @Column(name = "education")
    private String education="NA";

    @NotNull(message = "Experience cannot be empty")
    @Size(max = 1000, message = "work Experience must be less than 1000 characters")
    @Column(name = "work_experience")
    private String workExperience="NA";
    
    @NotNull(message = "Interest cannot be empty")
    @Size(max = 1000, message = "interest must be less than 1000 characters")
    @Column(name = "interest")
    private String interest="NA";

    DoctorDetails() {
    }

    DoctorDetails(String mobileNum, String education, String workExperience, String interest) {
        this.mobileNum = mobileNum;
        this.education = education;
        this.workExperience = workExperience;
        this.interest = interest;
    }

    public DoctorDetails(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "DoctorDetails [mobileNum=" + mobileNum + ", education=" + education + ", workExperience=" + workExperience + ", interest=" + interest + "]";
    }
    
}
