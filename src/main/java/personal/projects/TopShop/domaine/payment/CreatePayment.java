package personal.projects.TopShop.domaine.payment;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.projects.TopShop.service.models.LineProduct;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatePayment {

    private List<CreatePaymentItem> items;
    private String client;

    @Transient
    public Double getTotal(List<LineProduct> cart){
        List<Double> totals = new ArrayList<>();
        cart.forEach(createPaymentItem -> {
            totals.add(createPaymentItem.getTotalPrice() * createPaymentItem.getQuantity() * 100);
        });
        return totals
                .stream()
                .mapToDouble(Double::doubleValue).sum();
    }
}
