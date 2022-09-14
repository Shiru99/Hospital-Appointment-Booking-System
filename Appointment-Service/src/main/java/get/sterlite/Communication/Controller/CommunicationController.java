package get.sterlite.Communication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import get.sterlite.Communication.model.AppointmentDetailsResponse;
import get.sterlite.Communication.service.CommunicationService;

@RestController
@RequestMapping("/api/v1/appointments-ms")
class CommunicationController {

    @Autowired
    CommunicationService communicationService;

    @GetMapping(value = "/appointmentDetails/{appointmentId}", produces = "application/json")
    public AppointmentDetailsResponse getFullAppointmentDetailsByID(
            @PathVariable("appointmentId") int appointmentId) {
        return communicationService.getAppointmentDetailsByID(appointmentId);
    }
}