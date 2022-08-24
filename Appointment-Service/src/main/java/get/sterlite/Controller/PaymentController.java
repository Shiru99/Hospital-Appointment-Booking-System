package get.sterlite.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // @PostMapping("/pay")
    // public AppointmentResponse pay(@RequestBody @Valid AppointmentRequest appointmentRequest, BindingResult result) {
    //     if (result.hasErrors()) {
    //         throw new InvalidInputsException(result.getFieldErrors());
    //     }
    //     return paymentService.pay(appointmentRequest);
    // }
}
