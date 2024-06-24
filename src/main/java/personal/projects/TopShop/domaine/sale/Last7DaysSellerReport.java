package personal.projects.TopShop.domaine.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.projects.TopShop.service.models.DailyView;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Last7DaysSellerReport {

    private List<DailySales> dailySales;
    private List<DailyViews> dailyViews;
    private List<DailySoldCount> dailySoldCounts;

}
