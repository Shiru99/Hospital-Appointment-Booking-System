package get.sterlite.Communication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import get.sterlite.Authentication.util.JwtUtil;
import get.sterlite.Communication.model.AppointmentDetailsResponse;
import get.sterlite.Communication.model.Doctor.DoctorResponse;
import get.sterlite.Communication.model.Patient.PatientResponse;
import get.sterlite.model.AppointmentResponse;
import get.sterlite.service.AppointmentService;

@Service("CommunicationService")
public class CommunicationService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    protected JwtUtil jwtTokenUtil;

    @Autowired
    AppointmentService appointmentService;

    public AppointmentDetailsResponse getAppointmentDetailsByID(int appointmentId) {

        AppointmentResponse appointmentResponse = appointmentService.getAppointment(appointmentId);

        if (!appointmentResponse.getStatus().equals("success")) {
            return new AppointmentDetailsResponse(appointmentResponse.getStatus(), null, null, null, null);
        } else {

            AppointmentDetailsResponse appointmentDetailsResponse = new AppointmentDetailsResponse(
                    appointmentResponse.getStatus(), appointmentResponse.getAppointment(), null, null, null);

            HttpEntity<String> entity = createHeader();

            String patientId = appointmentResponse.getAppointment().getPatientId();
            getPatientDetails(patientId, entity, appointmentDetailsResponse);

            String doctorId = appointmentResponse.getAppointment().getDoctorId();
            getDoctorDetails(doctorId, entity, appointmentDetailsResponse);

            return appointmentDetailsResponse;
        }

    }

    private HttpEntity<String> createHeader() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + jwtTokenUtil.generateToken("0000000000"));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return entity;
    }

    private void getDoctorDetails(String doctorId, HttpEntity<String> entity,AppointmentDetailsResponse appointmentDetailsResponse) {

        ResponseEntity<DoctorResponse> doctorResponse = restTemplate.exchange(
                "http://localhost:9090/api/v1/doctors-ms/doctors/" + doctorId, HttpMethod.GET,
                entity, DoctorResponse.class);

        if (doctorResponse.getBody().getStatus().equals("success")) {
            appointmentDetailsResponse.setDoctor(doctorResponse.getBody().getDoctor());
            appointmentDetailsResponse.setDoctorDetails(doctorResponse.getBody().getDoctorDetails());
        } else {
            appointmentDetailsResponse.setStatus(doctorResponse.getBody().getStatus());
        }
    }

    private void getPatientDetails(String patientId, HttpEntity<String> entity, AppointmentDetailsResponse appointmentDetailsResponse) {

        ResponseEntity<PatientResponse> patientResponse = restTemplate.exchange(
                "http://localhost:9000/api/v1/patients-ms/patients/" + patientId, HttpMethod.GET,
                entity, PatientResponse.class);

        if (patientResponse.getBody().getStatus().equals("success")) {
            appointmentDetailsResponse.setPatient(patientResponse.getBody().getPatient());
        } else {
            appointmentDetailsResponse.setStatus(patientResponse.getBody().getStatus());
        }
    }

}
