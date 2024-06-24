package personal.projects.TopShop.service;


import personal.projects.TopShop.domaine.client.ClientVo;
import personal.projects.TopShop.domaine.client.CreateClientRequest;
import personal.projects.TopShop.domaine.client.UpdateClientRequest;
import personal.projects.TopShop.domaine.support.CreateSupportRequest;
import personal.projects.TopShop.domaine.support.SupportVo;
import personal.projects.TopShop.domaine.support.UpdateSupportRequest;

import java.util.List;

public interface ISupportService {

    List<SupportVo> getAllSupports();
    String createSupport(CreateSupportRequest request);
    SupportVo getSupportByIdentity(String identity);
    String deleteSupportByIdentity(String identity);
    String updateSupport(String identity, UpdateSupportRequest request);

}
