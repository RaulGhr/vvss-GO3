package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PizzaServiceTestStep3 {
    PaymentRepository payRepo;

    PizzaService service;

    PaymentValidator val;

    @BeforeEach
    void setUp() {
        payRepo = new PaymentRepository();
        val = new PaymentValidator();
        service = new PizzaService(null, payRepo, val);
    }

    @AfterEach
    void tearDown() {
        payRepo.getAll().clear();
        payRepo.writeAll();
    }

    @Test
    void getPayments() {
        List<Payment> result = service.getPayments();
        assertEquals(0, result.size());
    }

    @Test
    void addPayment() {
        Payment payment = new Payment(5, PaymentType.CASH, 55.5);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());

        List<Payment> result = service.getPayments();
        assertEquals(1, result.size());
        assertEquals(payment.getTableNumber(), result.get(0).getTableNumber());
    }
}
