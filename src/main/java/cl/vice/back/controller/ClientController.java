package cl.vice.back.controller;

import cl.vice.back.exception.InvalidInputException;
import cl.vice.back.model.Client;
import cl.vice.back.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping(value = "getAllClients")
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @GetMapping(value = "listClientsIds")
    public ResponseEntity<List<Integer>> listClientsIds() {
        try {
            return new ResponseEntity<>(clientService.listClientsIds(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @GetMapping(value = "listClientsIdsSortedByRUT")
    public ResponseEntity<List<Integer>> listClientsIdsSortedByRUT() {
        try {
            return new ResponseEntity<>(clientService.listClientsIdsSortedByRUT(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @GetMapping(value = "higherClientsBalances")
    public ResponseEntity<List<Integer>> higherClientsBalances() {
        try {
            return new ResponseEntity<>(clientService.higherClientsBalances(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @GetMapping(value = "sortClientsTotalBalances")
    public ResponseEntity<List<String>> sortClientsTotalBalances() {
        try {
            return new ResponseEntity<>(clientService.sortClientsTotalBalances(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @GetMapping(value = "insuranceClientsByRUT")
    public ResponseEntity<Map<String, List<String>>> insuranceClientsByRUT() {
        try {
            return new ResponseEntity<>(clientService.insuranceClientsByRUT(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @GetMapping(value = "uniqueInsurance")
    public ResponseEntity<Map<String, Long>> uniqueInsurance() {
        try {
            return new ResponseEntity<>(clientService.uniqueInsurance(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @GetMapping(value = "clientWithLessFunds")
    public ResponseEntity<Map<String, Integer>> clientWithLessFunds() {
        try {
            return new ResponseEntity<>(clientService.clientWithLessFunds(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @PostMapping(value = "createClient")
    public ResponseEntity<Client> createClient(Client client) {
        try {
            return new ResponseEntity<>(clientService.createClient(client), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }

    @PostMapping(value = "saveClients")
    public ResponseEntity<List<Client>> saveClients() {
        try {
            return new ResponseEntity<>(clientService.saveClients(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Client.class.getSimpleName());
        }
    }
}
