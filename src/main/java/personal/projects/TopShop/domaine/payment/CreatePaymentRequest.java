package personal.projects.TopShop.domaine.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    private String username;
    private Long productId;
    private int quantity;
}
