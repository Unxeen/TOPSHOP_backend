package personal.projects.TopShop.service;

import org.springframework.web.multipart.MultipartFile;
import personal.projects.TopShop.domaine.product.CreateProductRequest;
import personal.projects.TopShop.domaine.product.ProductVo;
import personal.projects.TopShop.domaine.product.SearchProductsRequest;
import personal.projects.TopShop.domaine.product.UpdateProductRequest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IProductService {

    List<ProductVo> searchProducts(SearchProductsRequest request);
    ProductVo findById(Long id);
    ProductVo findByLabel(String label);
    String addView(Long id);
    String save(CreateProductRequest request) throws IOException;
    List<ProductVo> getAllByUsername(String username);
    String delete(Long id);
    ProductVo update(Long id, UpdateProductRequest request);
}
