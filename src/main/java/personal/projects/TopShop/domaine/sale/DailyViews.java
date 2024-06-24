package personal.projects.TopShop.domaine.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailyViews {

    private String date;
    private Integer viewsCount;
    private int day;

}
