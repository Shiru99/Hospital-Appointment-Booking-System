package get.sterlite.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int appointmentId;

    @NotNull(message = "doctor_id cannot be null")
    @Size(min = 10, max = 10, message = "doctor_id must be 10 digits")
    @Column(name = "doctor_id", length = 10)
    private String doctorId;

    @NotNull(message = "patient_id cannot be null")
    @Size(min = 10, max = 10, message = "patient_id must be 10 digits")
    @Column(name = "patient_id", length = 10)
    private String patientId;

    @NotNull(message = "appointment_date cannot be null")
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    @Column(name = "slot")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Slot must be specified")
    private Slot slot = Slot.UNKNOWN;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "status must be specified")
    private Status status = Status.UNKNOWN;

    @NotNull(message = "message must be specified")
    @Size(min = 1, max = 1000, message = "message cannot be empty")
    @Column(name = "message")
    private String message;

    @Column(name = "paid")
    @Enumerated(EnumType.STRING)
    private Paid paid = Paid.UNKNOWN;

    public Appointment() {
    }

    public Appointment(String doctorId, String patientId, Date appointmentDate, Slot slot, Status status,
            String message) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.slot = slot;
        this.status = status;
        this.message = message;
    }

    public Appointment(String doctorId, String patientId, Date appointmentDate, Slot slot, Status status,
            String message, Paid paid) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.slot = slot;
        this.status = status;
        this.message = message;
        this.paid = paid;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Paid getPaid() {
        return paid;
    }

    public void setPaid(Paid paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", doctorId=" + doctorId + ", patientId=" + patientId
                + ", appointmentDate=" + appointmentDate + ", slot=" + slot + ", status=" + status + ", message="
                + message + ", paid=" + paid + "]";

    }

}
