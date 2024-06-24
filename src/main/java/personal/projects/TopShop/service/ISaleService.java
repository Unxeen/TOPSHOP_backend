package personal.projects.TopShop.service;

import personal.projects.TopShop.domaine.sale.AllSalesResponse;
import personal.projects.TopShop.domaine.sale.DailySales;
import personal.projects.TopShop.domaine.sale.Last7DaysSellerReport;
import personal.projects.TopShop.domaine.sale.SaleVo;

import java.util.Date;
import java.util.List;

public interface ISaleService {

    AllSalesResponse getSalesByUsername(String username);
    List<SaleVo> getSalesByUsernameAndDate(String username, String date);
    Last7DaysSellerReport getSalesForLast7DaysByUsername(String username);

}
