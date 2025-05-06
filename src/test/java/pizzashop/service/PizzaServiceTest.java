package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PizzaServiceTest {

    private PizzaService service;
    private PaymentRepository repository;
    private PaymentValidator validator;

    @BeforeEach
    void setUp() {
        MenuRepository repoMenu = new MenuRepository();
        this.repository = new PaymentRepository();
        this.validator = new PaymentValidator();
        this.service = new PizzaService(repoMenu, repository, validator);

        repository.getAll().clear();
        repository.writeAll();
    }

    @Test
    @Order(1)
    @DisplayName("EC Test 1")
        // @Disabled
    void addValidPaymentTest() {
        service.addPayment(1, PaymentType.CARD, 123.4);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(2)
    @DisplayName("EC Test 2")
    void addValidPaymentTest2() {
        service.addPayment(4, PaymentType.CASH, 1234.5);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(3)
    @DisplayName("EC Test 3")
    void addValidPaymentTest3() {
        service.addPayment(6, PaymentType.CASH, 15.7);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(4)
    @DisplayName("EC Test 4")
    void addValidPaymentTest4() {
        service.addPayment(8, PaymentType.CARD, 23.9);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(5)
    @DisplayName("EC Test 5")
    void addInvalidPayment() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(-10, PaymentType.CARD, 500.4);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }


    @Test
    @Order(6)
    @DisplayName("EC Test 6")
    void addInvalidPayment2() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(121, PaymentType.CASH, 455);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(7)
    @DisplayName("EC Test 7")
    void addInvalidPayment3() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(4, PaymentType.CASH, 15000);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 10000."), "Exception message does not match expected text.");
    }

    @Test
    @Order(8)
    @DisplayName("EC Test 8")
    void addInvalidPayment4() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(7, PaymentType.CARD, -7.5);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 10000."), "Exception message does not match expected text.");

    }


    @Test
    @Order(9)
    @Tag("BVA")
    @DisplayName("BVA Test 1")
    void addValidPaymentTestBVA() {
        service.addPayment(2, PaymentType.CARD, 23.3);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(10)
    @DisplayName("BVA Test 2")
    void addInvalidPaymentBVA() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(9, PaymentType.CASH, 23.3);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(11)
    @DisplayName("BVA Test 3")
    void addInvalidPaymentBVA2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(0, PaymentType.CARD, 23.3);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(12)
    @DisplayName("BVA Test 4")
    @EnabledOnOs(OS.WINDOWS)
    void addValidPaymentTestBVA2() {
        service.addPayment(4, PaymentType.CASH, 123.45);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(13)
    @DisplayName("BVA Test 5")
    @EnabledOnOs(OS.WINDOWS)
    void addInvalidPaymentBVA3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(4, PaymentType.CARD, 0);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 10000."), "Exception message does not match expected text.");
    }


    @Test
    @Order(14)
    @DisplayName("BVA Test 6")
    @EnabledOnOs(OS.WINDOWS)
    void addInvalidPaymentBVA4() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(4, PaymentType.CARD, 10000.1);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 10000."), "Exception message does not match expected text.");
    }




}