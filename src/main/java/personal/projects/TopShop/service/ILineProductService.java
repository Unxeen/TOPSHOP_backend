package personal.projects.TopShop.service;

import personal.projects.TopShop.domaine.cart.CreateLineProductRequest;
import personal.projects.TopShop.domaine.cart.LineProductVo;
import personal.projects.TopShop.domaine.cart.UpdateLineProductRequest;
import personal.projects.TopShop.service.models.LineProduct;

import java.util.List;

public interface ILineProductService {

    List<LineProductVo> getAllByUsername(String username);
    String saveLP(CreateLineProductRequest request);
    LineProductVo getById(Long id);
    String updateLP(UpdateLineProductRequest request, Long id);
    String deleteLP(Long id);
}
