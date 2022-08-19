package get.sterlite.model;

public class DoctorResponse {
    private String status;
    private Doctor Doctor;

    public DoctorResponse(String status, Doctor Doctor) {
        this.status = status;
        this.Doctor = Doctor;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Doctor getDoctor() {
        return Doctor;
    }
    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }
}
