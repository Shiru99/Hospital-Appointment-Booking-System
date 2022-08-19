package get.sterlite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Patients")
public class Patient {

    @Id
    @Column(name = "patient_id", nullable = false)
    private String patientID;

    @Column(name = "full_name")
    private String patientName;

    public Patient() {
    }

    public Patient(String patientID, String patientName) {
        this.patientID = patientID;
        this.patientName = patientName;
    }

    @Override
    public String toString() {
        return "Patient [patientID=" + patientID + ", patientName=" + patientName + "]";
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

}
