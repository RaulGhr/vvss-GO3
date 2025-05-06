package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentTestStep1 {

    Payment payment;
    @BeforeEach
    void setUp() {
        payment = new Payment(5, PaymentType.CASH,55.5f);
    }

    @Test
    void getTableNumber() {
        assertEquals(5, payment.getTableNumber());
    }

    @Test
    void setTableNumber() {
        payment.setTableNumber(6);
        assertEquals(6, payment.getTableNumber());
    }
}
