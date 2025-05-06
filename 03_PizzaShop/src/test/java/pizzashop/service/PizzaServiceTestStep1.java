package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PizzaServiceTestStep1 {

    @Mock
    PaymentRepository payRepo;

    @InjectMocks
    PizzaService service;

    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();
        when(payRepo.getAll()).thenReturn(payments);
    }

    @Test
    void getPayments() {
        List<Payment> result = service.getPayments();
        assertEquals(0, result.size());
        verify(payRepo, times(1)).getAll();
    }

    @Test
    void addPayment() {
        Payment payment = new Payment(5, PaymentType.CASH,55.5);
        doAnswer((invocation)->{
            payments.add(payment);
            return null;
        }).when(payRepo).add(any(Payment.class));

        service.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        verify(payRepo,times(1)).add(any());

        List<Payment> result=service.getPayments();
        assertEquals(1,result.size());
        assertEquals(payment, result.get(0));
    }
}