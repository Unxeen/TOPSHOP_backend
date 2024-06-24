package personal.projects.TopShop.domaine.sale;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.projects.TopShop.domaine.client.ClientVo;
import personal.projects.TopShop.domaine.product.ProductVo;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleVo {

    private Long id;
    private ProductVo product;
    private Double totalPrice;
    private Integer quantity;
    private ClientVo owner;
    private ClientVo buyer;
    private String createdAt;

}
