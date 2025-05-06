package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PizzaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PizzaServiceTestGetTotalAmount {

    private PizzaService service;

    @BeforeEach
    void setUp() {
        service = new PizzaService(null, null, null);
    }

    @Test
    @Order(1)
    void getTotalAmountTest1() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(5, PaymentType.CASH, 13.0f));
        double result = service.getTotalAmount(paymentList, PaymentType.CASH);
        assertEquals(13.0f, result);
    }

    @Test
    @Order(2)
    void getTotalAmountTest2() {
        List<Payment> paymentList = new ArrayList<>();
        double result = service.getTotalAmount(paymentList, PaymentType.CASH);
        assertEquals(0.0f, result);
    }

    @Test
    @Order(3)
    void getTotalAmountTest3() {
        double result = service.getTotalAmount(null, PaymentType.CASH);
        assertEquals(0.0f, result);
    }

    @Test
    @Order(4)
    void getTotalAmountTest4() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(1, PaymentType.CARD, 12.0f));
        paymentList.add(new Payment(2, PaymentType.CASH, 13.0f));
        paymentList.add(new Payment(3, PaymentType.CARD, 12.0f));
        double result = service.getTotalAmount(paymentList, PaymentType.CASH);
        assertEquals(13.0f, result);
    }

    @Test
    @Order(5)
    void getTotalAmountTest5() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(5, PaymentType.CASH, 13.0f));
        double result = service.getTotalAmount(paymentList, PaymentType.CARD);
        assertEquals(0.0f, result);
    }

    @Test
    @Order(6)
    void getTotalAmountTest6() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(5, PaymentType.CASH, 13.0f));
        try {
            double result = service.getTotalAmount(paymentList, null);
        }catch (Exception e){
            assertTrue(true);
        }
    }

}