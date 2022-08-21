package get.sterlite.model;

import javax.validation.Valid;

public class AppointmentRequest {

    @Valid
    private Appointment appointment;

    public AppointmentRequest() {
    }

    public AppointmentRequest(Appointment appointment) {
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "AppointmentRequest{" + "appointment=" + appointment + '}'; 
    }
}
