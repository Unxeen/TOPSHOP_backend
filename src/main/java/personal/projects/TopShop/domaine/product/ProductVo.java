package personal.projects.TopShop.domaine.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.projects.TopShop.domaine.client.ClientVo;
import personal.projects.TopShop.service.models.Category;
import personal.projects.TopShop.service.models.Client;
import personal.projects.TopShop.service.models.ProductImage;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductVo {

    private Long id;
    private String label;
    private ProductImage image;
    private String description;
    private Double price;
    private Integer stock;
    private Integer views;
    private ClientVo owner;
    private Category category;

}
