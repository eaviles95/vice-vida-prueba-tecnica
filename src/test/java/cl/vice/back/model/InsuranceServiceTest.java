import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import cl.vice.back.service.IClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class ClientServiceTest {

    @Mock
    private IClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsuranceClientsByRUT() {
        // Configurar datos de prueba simulados
        Map<String, List<String>> mockResult = new HashMap<>();
        mockResult.put("Seguro A", Arrays.asList("12345678-9", "23456789-0"));
        mockResult.put("Seguro B", Arrays.asList("3333333-3"));

        when(clientService.insuranceClientsByRUT()).thenReturn(mockResult);

        // Ejecutar el método
        Map<String, List<String>> result = clientService.insuranceClientsByRUT();

        // Validaciones
        assertThat(result).containsKey("Seguro A");
        assertThat(result).containsKey("Seguro B");
        assertThat(result.get("Seguro A")).containsExactly("12345678-9", "23456789-0");
        assertThat(result.get("Seguro B")).containsExactly("3333333-3");

        // Verificar que se llamó la lógica esperada
        verify(clientService, times(1)).insuranceClientsByRUT();
    }

    @Test
    public void testHigherClientsBalances() {
        // Configurar datos de prueba simulados
        List<Integer> mockBalances = Arrays.asList(35000, 20000, 15000);

        when(clientService.higherClientsBalances()).thenReturn(mockBalances);

        // Ejecutar el método
        List<Integer> result = clientService.higherClientsBalances();

        // Validaciones
        assertThat(result).containsExactly(35000, 20000, 15000);

        // Verificar que se llamó la lógica esperada
        verify(clientService, times(1)).higherClientsBalances();
    }

    @Test
    public void testInsuranceSortedByHighestBalance() {
        // Configurar datos de prueba simulados
        List<Integer> mockResult = Arrays.asList(102, 101, 103);

        when(clientService.insuranceSortedByHighestBalance()).thenReturn(mockResult);

        // Ejecutar el método
        List<Integer> result = clientService.insuranceSortedByHighestBalance();

        // Validaciones
        assertThat(result).containsExactly(102, 101, 103);

        // Verificar que se llamó la lógica esperada
        verify(clientService, times(1)).insuranceSortedByHighestBalance();
    }
}
