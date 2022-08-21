package get.sterlite.model;

public class AppointmentResponse {
    private String status="NA";
    private Appointment appointment;

    public AppointmentResponse() {
    }

    public AppointmentResponse(String status, Appointment appointment) {
        this.status = status;
        this.appointment = appointment;
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

    @Override
    public String toString() {
        return "AppointmentResponse{" +  "status='" + status + '\'' + ", appointment=" + appointment + '}';
    }
}
