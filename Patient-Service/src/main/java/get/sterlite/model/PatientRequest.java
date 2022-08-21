package get.sterlite.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PatientRequest {

    @NotNull
    @Valid
    private Patient patient;

    public PatientRequest() {
    }

    public PatientRequest(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
