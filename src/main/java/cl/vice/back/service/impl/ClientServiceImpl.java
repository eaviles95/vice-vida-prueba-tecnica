package cl.vice.back.service.impl;

import cl.vice.back.model.Account;
import cl.vice.back.model.Client;
import cl.vice.back.model.Insurance;
import cl.vice.back.repository.IClientRepository;
import cl.vice.back.service.IAccountService;
import cl.vice.back.service.IClientService;
import cl.vice.back.service.IInsuranceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;
    private final IAccountService accountService;
    private final IInsuranceService insuranceService;

    @Autowired
    public ClientServiceImpl(IClientRepository clientRepository, IAccountService accountService, IInsuranceService insuranceService) {

        this.clientRepository = clientRepository;
        this.accountService = accountService;
        this.insuranceService = insuranceService;

    }

    public Client createClient(Client client) {
        Client clientAux = new Client();
        clientAux.setId(client.getId());
        clientAux.setRut(client.getRut());
        clientAux.setName(client.getName());
        clientAux = clientRepository.save(clientAux);
        return clientAux;
    }

    public List<Client> saveClients() {
        List<Client> clientes = Arrays.asList(
                new Client(1, "DANIEL BUSTOS", "8662085-5"),
                new Client(2, "NICOLAS PEREZ","7317855-K"),
                new Client(3, "ERNESTO GRANADO", "7382649-7"),
                new Client(4, "JORDAN MARTINEZ", "8858771-5"),
                new Client(5, "ALEJANDRO ZELADA", "9402019-0"),
                new Client(6, "DENIS ROJAS", "9980423-8")
        );

        List<Client> clientesGuardados = new ArrayList<>();

        for (Client cliente : clientes) {
            Client clienteGuardado = createClient(cliente);
            clientesGuardados.add(clienteGuardado);
        }
        return clientesGuardados;
    }


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Integer> listClientsIds() {
        List<Client> clients = getAllClients();
        return clients.stream()
                .map(Client::getId)
                .collect(Collectors.toList());
    }

    public List<Integer> listClientsIdsSortedByRUT() {
        List<Client> clients = getAllClients();

        return clients.stream()
                .sorted(Comparator.comparingInt(client -> Integer.parseInt(client.getRut().split("-")[0])))
                .map(Client::getId)
                .collect(Collectors.toList());
    }

    public List<String> sortClientsTotalBalances() {
        return accountService.getAllAccounts().stream()
                .collect(Collectors.groupingBy(
                        Account::getClientId,
                        Collectors.summingInt(Account::getBalance)
                ))
                .entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(entry -> getClientNameById(entry.getKey()))
                .collect(Collectors.toList());
    }

    public Map<String, List<String>> insuranceClientsByRUT() {
        List<Account> accounts = accountService.getAllAccounts();
        List<Client> clients = clientRepository.findAll();

        Map<String, List<String>> insuranceMap = new HashMap<>();

        for (Account account : accounts) {
            Client client = clients.stream()
                    .filter(c -> c.getId().equals(account.getClientId()))
                    .findFirst()
                    .orElse(null);

            if (client != null) {
                String insuranceName = account.getInsuranceId().toString();
                insuranceMap.putIfAbsent(insuranceName, new ArrayList<>());
                insuranceMap.get(insuranceName).add(client.getRut());
            }
        }

        insuranceMap.forEach((key, value) -> Collections.sort(value));

        return insuranceMap;
    }


    private  String getClientNameById(Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + clientId));
        return client.getName();
    }

    public List<Integer> higherClientsBalances() {
        List<Account> accounts = accountService.getAllAccounts();
        List<Insurance> insurances = insuranceService.getAllInsurances();
        Integer seguroAPVId = insurances.stream()
                .filter(insurance -> "Seguro APV".equalsIgnoreCase(insurance.getName()))
                .findFirst()
                .map(Insurance::getId)
                .orElseThrow(() -> new RuntimeException("Seguro APV no encontrado"));

        return accounts.stream()
                .filter(account -> account.getInsuranceId().equals(seguroAPVId) && account.getBalance() > 30000)
                .map(Account::getBalance)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public  Map<String, Long> uniqueInsurance() {
        List<Account> allAccounts = accountService.getAllAccounts();
        List<Client> allClients = getAllClients();

        Map<String, Set<Integer>> clientToInsuranceMap = allAccounts.stream()
                .collect(Collectors.groupingBy(
                        account -> account.getClientId().toString(),
                        Collectors.mapping(Account::getInsuranceId, Collectors.toSet())
                ));

        Map<String, Long> insuranceCountMap = new HashMap<>();

        for (Map.Entry<String, Set<Integer>> entry : clientToInsuranceMap.entrySet()) {
            Set<Integer> insuranceIds = entry.getValue();

            if (insuranceIds.size() == 1) {
                Integer insuranceId = insuranceIds.iterator().next();
                insuranceCountMap.put(
                        getInsuranceNameById(insuranceId),
                        insuranceCountMap.getOrDefault(getInsuranceNameById(insuranceId), 0L) + 1
                );
            }
        }
        return insuranceCountMap;
    }

    public  Map<String, Integer> clientWithLessFunds() {
        List<Account> allAccounts = accountService.getAllAccounts();
        List<Client> allClients = getAllClients();

        Map<Integer, List<Account>> accountsByInsurance = allAccounts.stream()
                .collect(Collectors.groupingBy(Account::getInsuranceId));

        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<Integer, List<Account>> entry : accountsByInsurance.entrySet()) {
            List<Account> accounts = entry.getValue();

            Account accountWithMinBalance = accounts.stream()
                    .min(Comparator.comparingInt(Account::getBalance))
                    .orElse(null);

            if (accountWithMinBalance != null) {
                String insuranceName = getInsuranceNameById(entry.getKey());
                result.put(insuranceName, accountWithMinBalance.getClientId());
            }
        }
        return result;
    }

    private String getInsuranceNameById(Integer insuranceId) {
        return insuranceService.getInsuranceNameById(insuranceId);
    }
}
