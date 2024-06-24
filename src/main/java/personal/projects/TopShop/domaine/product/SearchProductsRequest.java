package personal.projects.TopShop.domaine.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchProductsRequest {

    private String label;
    private Double price1;
    private Double price2;
    private String category;
    private Integer index;
    private Integer size;

}
