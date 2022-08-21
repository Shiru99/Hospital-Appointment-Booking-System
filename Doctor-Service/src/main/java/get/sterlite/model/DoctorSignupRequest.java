package get.sterlite.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DoctorSignupRequest {

    @Valid
    @NotNull(message = "Doctor Name cannot be null")
    public Doctor doctor;

    public DoctorSignupRequest(){
    }

    public DoctorSignupRequest( Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
