package personal.projects.TopShop.domaine.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailySoldCount {

    private String date;
    private Long soldCount;
    private int day;

}
