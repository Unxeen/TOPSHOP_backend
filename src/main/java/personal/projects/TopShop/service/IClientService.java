package personal.projects.TopShop.service;

import personal.projects.TopShop.domaine.client.ClientVo;
import personal.projects.TopShop.domaine.client.CreateClientRequest;
import personal.projects.TopShop.domaine.client.UpdateClientRequest;
import personal.projects.TopShop.domaine.user.TokenVo;
import personal.projects.TopShop.service.models.Client;

import java.io.IOException;
import java.util.List;

public interface IClientService {

    List<ClientVo> getAllClients();
    String createClient(CreateClientRequest request);
    ClientVo getClientByUsername(String username);
    ClientVo getClientByIdentity(String identity);
    String deleteClientByIdentity(String identity);
    TokenVo updateClient(String username, UpdateClientRequest request) throws IOException;

}