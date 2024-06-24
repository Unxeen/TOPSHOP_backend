package personal.projects.TopShop.domaine.cart;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.projects.TopShop.service.models.Product;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineProductVo {

    private Long id;
    private Product product;
    private Integer quantity;
    private Double totalPrice;

}
