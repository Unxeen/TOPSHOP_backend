package personal.projects.TopShop.domaine.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import personal.projects.TopShop.service.models.Category;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateProductRequest {

    private String label;
    private String description;
    private Double price;
    private MultipartFile image;
    private int stock;
    private String username;
    private String cat;

}
