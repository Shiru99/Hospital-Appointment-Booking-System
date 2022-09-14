package get.sterlite.Communication.model.Doctor;

public class DoctorResponse {
    private String status="NA";
    public Doctor doctor;
    public DoctorDetails doctorDetails;

    public DoctorResponse(){
        
    }

    public DoctorResponse(String status, Doctor doctor) {
        this.status = status;
        this.doctor = doctor;
    }

    public DoctorResponse( Doctor doctor, DoctorDetails doctorDetails) {
        this.doctor = doctor;
        this.doctorDetails = doctorDetails;
    }

    public DoctorResponse(String status, Doctor doctor, DoctorDetails doctorDetails) {
        this.status = status;
        this.doctor = doctor;
        this.doctorDetails = doctorDetails;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
