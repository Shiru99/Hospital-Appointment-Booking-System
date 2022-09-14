package get.sterlite.model;

import javax.validation.Valid;
// import javax.validation.constraints.NotNull;

public class DoctorRequest {
    
    @Valid
    // @NotNull(message = "Doctor cannot be null")
    public Doctor doctor;

    @Valid
    // @NotNull(message = "Doctor Details cannot be null")
    public DoctorDetails doctorDetails;

    public DoctorRequest(){
        
    }

    public DoctorRequest( Doctor doctor) {
        this.doctor = doctor;
    }

    public DoctorRequest( Doctor doctor, DoctorDetails doctorDetails) {
        this.doctor = doctor;
        this.doctorDetails = doctorDetails;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DoctorDetails getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }
}
