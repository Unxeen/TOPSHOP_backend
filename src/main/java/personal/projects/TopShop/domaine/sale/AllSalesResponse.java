package personal.projects.TopShop.domaine.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllSalesResponse {

    private List<SaleVo> sales;
    private Double total;
    private Integer quantityTotal;
    private Integer totalViews;

}
