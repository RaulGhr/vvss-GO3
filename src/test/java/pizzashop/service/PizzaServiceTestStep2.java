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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PizzaServiceTestStep2 {

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
        Payment mockPayment = mock(Payment.class);

        payRepo.add(mockPayment);

        List<Payment> result = service.getPayments();
        assertEquals(1, result.size());
        assertEquals(mockPayment, result.get(0));
    }
}