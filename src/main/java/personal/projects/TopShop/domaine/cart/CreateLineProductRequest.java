package personal.projects.TopShop.domaine.cart;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateLineProductRequest {

    private String username;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;

}
