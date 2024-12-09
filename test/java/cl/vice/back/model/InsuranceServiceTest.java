import cl.vice.back.model.Account;
import cl.vice.back.model.Client;
import cl.vice.back.model.Insurance;
import cl.vice.back.service.IAccountService;
import cl.vice.back.service.IInsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InsuranceServiceTest {


    @Mock
    private IAccountService accountService;

    @Mock
    private IInsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        accountService = mock(IAccountService.class);
        insuranceService = mock(IInsuranceService.class);
    }

    @Test
    void testInsuranceSortedByHighestBalance() {

        List<Account> mockAccounts = Arrays.asList(
                new Account(1, 6, 1, 15000),
                new Account(2, 3, 1, 18000),
                new Account(3, 3, 1, 135000),
                new Account(4, 2, 2, 5600),
                new Account(5, 1, 3, 23000),
                new Account(6, 2, 2, 15000),
                new Account(7, 3, 3, 45900),
                new Account(8, 3, 3, 19000)
        );

        List<Insurance> mockInsurances = Arrays.asList(
                new Insurance(1, "Seguro APV"),
                new Insurance(2, "Seguro de Vida"),
                new Insurance(3, "Seguro Complementario de Salud")
        );

        when(accountService.getAllAccounts()).thenReturn(mockAccounts);
        when(insuranceService.getAllInsurances()).thenReturn(mockInsurances);

        List<Integer> expectedOrder = Arrays.asList(3, 2, 1);

        List<Integer> result = insuranceService.insuranceSortedByHighestBalance();

        assertNotNull(result, "El resultado no debe ser null");
        assertEquals(expectedOrder.size(), result.size(), "El tamaño de la lista no coincide");
        assertIterableEquals(expectedOrder, result, "El orden de los IDs de los seguros no coincide con el esperado");
    }
}