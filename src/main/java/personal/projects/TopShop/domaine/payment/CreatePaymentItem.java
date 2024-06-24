package personal.projects.TopShop.domaine.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatePaymentItem {
    private Long id;
    private Long unitPrice;
    private int quantity;

}
