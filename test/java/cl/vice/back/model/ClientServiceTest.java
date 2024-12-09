import cl.vice.back.model.Client;
import cl.vice.back.model.ClientServiceMock;
import cl.vice.back.service.IClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {

    private IClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientServiceMock();
    }

    @Test
    public void testInsuranceClientsByRUT() {
        List<Client> clients = Arrays.asList(
                new Client(1, "12345678-9", "Juan Perez"),
                new Client(2, "98765432-1", "Maria Lopez"),
                new Client(3, "33333333-3", "Pedro Gonzalez")
        );

        Map<String, List<String>> result = clientService.insuranceClientsByRUT();

        assertNotNull(result);
        assertTrue(result.containsKey("SEGURO APV"));

        List<String> clientRuts = result.get("SEGURO APV");

        List<String> expected = Arrays.asList("12345678-9", "33333333-3", "98765432-1");

        assertEquals(expected, clientRuts);
    }
}
