package wei.web.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import wei.web.domain.Payment;

@RestController
@RequestMapping("/payment-service")
public class PaymentResource {

    private static final Logger logger = LoggerFactory.getLogger(PaymentResource.class);

    @GetMapping("/payments/{id}")
    public Mono<Payment> getOrder(@PathVariable String id) {
        Payment payment = new Payment();
        payment.setId(id);
        return Mono.just(payment);
    }

    @PostMapping("/payments")
    public Mono<Void> createOrder(@RequestBody Payment payment) {
        logger.info("Have created payment for {}", payment.getMethod());
        return Mono.empty();
    }
}