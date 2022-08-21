package get.sterlite.model;

public class DoctorRequest {
    public Doctor doctor;
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
