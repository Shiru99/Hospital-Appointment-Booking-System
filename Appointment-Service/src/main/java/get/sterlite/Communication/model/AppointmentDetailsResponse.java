package get.sterlite.Communication.model;

import get.sterlite.Communication.model.Doctor.Doctor;
import get.sterlite.Communication.model.Doctor.DoctorDetails;
import get.sterlite.Communication.model.Patient.Patient;
import get.sterlite.model.Appointment;

public class AppointmentDetailsResponse {
    private String status="NA";
    private Appointment appointment;
    private Patient patient;
    private Doctor doctor;
    private DoctorDetails doctorDetails;

    public AppointmentDetailsResponse() {
    }

    public AppointmentDetailsResponse(String status, Appointment appointment, Patient patient, Doctor doctor, DoctorDetails doctorDetails) {
        this.status = status;
        this.appointment = appointment;
        this.patient = patient;
        this.doctor = doctor;
        this.doctorDetails = doctorDetails;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    @Override
    public String toString() {
        return "AppointmentDetailsResponse [appointment=" + appointment + ", doctor=" + doctor + ", doctorDetails="
                + doctorDetails + ", status=" + status + "]";
    }
}
