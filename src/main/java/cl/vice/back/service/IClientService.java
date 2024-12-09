package cl.vice.back.service;

import cl.vice.back.model.Client;

import java.util.List;
import java.util.Map;

public interface IClientService {

    public Client createClient(Client client);

    public List<Client> getAllClients();

    public List<Integer> listClientsIds();

    public List<Integer> listClientsIdsSortedByRUT();

    public List<Integer> higherClientsBalances();

    public List<Client> saveClients();

    public List<String> sortClientsTotalBalances();

    public Map<String, List<String>> insuranceClientsByRUT();

    public Map<String, Long> uniqueInsurance();

    public  Map<String, Integer> clientWithLessFunds();

    }
