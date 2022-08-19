package get.sterlite.model;

public class PatientResponse {
    private String status;
    private Patient patient;

    public PatientResponse(String status, Patient patient) {
        this.status = status;
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
