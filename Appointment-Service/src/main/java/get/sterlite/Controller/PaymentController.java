package get.sterlite.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.model.AppointmentResponse;
import get.sterlite.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
class PaymentController {
    
    private String appName = "Payment Service";

    @Value("${greetings}")
    private String greetings;

    @GetMapping("")
    public String index() {
        return greetings + " from " + appName;
    }

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/pay/{appointmentId}", produces = "application/json")
    public AppointmentResponse payForAppointment(@PathVariable("appointmentId") int appointmentId) {
        return paymentService.verifyPayment(appointmentId);
    }
}
