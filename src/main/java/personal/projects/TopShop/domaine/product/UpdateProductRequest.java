package personal.projects.TopShop.domaine.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.projects.TopShop.service.models.Category;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateProductRequest {

    private String label;
    private String description;
    private Double price;
    private int stock;
    private String cat;

}
