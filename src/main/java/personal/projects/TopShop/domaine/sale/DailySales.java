package personal.projects.TopShop.domaine.sale;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailySales {

    private String date;
    private Double totalSales;
    private int day;
}
