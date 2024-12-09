package cl.vice.back.model;

import cl.vice.back.service.IAccountService;
import cl.vice.back.service.IClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    private IClientService clientService;
    private IAccountService accountService;


    @BeforeEach
    void setUp() {
        clientService = mock(IClientService.class);
        accountService = mock(IAccountService.class);
    }

    @Test
    public void testInsuranceClientsByRUT() {
        List<Client> clients = Arrays.asList(
                new Client(1, "12345678-9", "Juan Perez"),
                new Client(2, "98765432-1", "Maria Lopez"),
                new Client(3, "33333333-3", "Pedro Gonzalez")
        );

        Map<String, List<String>> expectedResult = new HashMap<>();
        expectedResult.put("SEGURO APV", Arrays.asList("12345678-9", "33333333-3", "98765432-1"));

        when(clientService.insuranceClientsByRUT()).thenReturn(expectedResult);

        Map<String, List<String>> result = clientService.insuranceClientsByRUT();

        assertNotNull(result);
        assertTrue(result.containsKey("SEGURO APV"));
        assertEquals(expectedResult, result);
    }

    @Test
    void testHigherClientsBalances() {
        // Datos de prueba mockeados
        List<Account> mockAccounts = Arrays.asList(
                new Account(1, 1, 1, 45000),  // Balance > 30,000
                new Account(2, 2, 1, 20000),  // No cumple
                new Account(3, 3, 1, 31000),  // Balance > 30,000
                new Account(4, 4, 1, 15000),  // No cumple
                new Account(5, 5, 1, 55000)   // Balance > 30,000
        );

        // Configuración del mock
        when(accountService.getAllAccounts()).thenReturn(mockAccounts);

        // Resultado esperado
        List<Integer> expectedBalances = Arrays.asList(55000, 45000, 31000);

        // Ejecución del método
        List<Integer> result = clientService.higherClientsBalances();

        // Validaciones
        assertNotNull(result, "El resultado no debe ser null");
        assertEquals(expectedBalances.size(), result.size(), "La cantidad de elementos no coincide");

        // Verificar que los valores están en el orden correcto
        assertIterableEquals(expectedBalances, result, "Los balances no coinciden con el orden esperado");

        // Verificar que todos los saldos sean mayores a 30,000
        assertTrue(result.stream().allMatch(balance -> balance > 30000),
                "Todos los balances deben ser mayores a 30,000");
    }
}
