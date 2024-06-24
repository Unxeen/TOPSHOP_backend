package personal.projects.TopShop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import personal.projects.TopShop.dao.DailyViewRepository;
import personal.projects.TopShop.dao.SaleRepository;
import personal.projects.TopShop.domaine.sale.*;
import personal.projects.TopShop.service.models.DailyView;
import personal.projects.TopShop.service.models.Sale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class SaleServiceImpl implements ISaleService{

    private SaleRepository saleRepository;
    private DailyViewRepository dailyViewRepository;
    private ModelMapper modelMapper;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Last7DaysSellerReport getSalesForLast7DaysByUsername(String username) {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.add(Calendar.DAY_OF_YEAR, -5);
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -11);
        Date startDate = calendar.getTime();
        LocalDate localStartDate = LocalDate.now().minusDays(7);


//      FETCH ALL SALES MADE IN THE LAST 7 DAYS
        List<Sale> sales = saleRepository.findAllByOwner_UsernameAndCreatedAtBetween(
                username,
                startDate,
                endDate);


//      EXTRACT DAILY SALES REPORT
        List<DailySales> dailySales = sales.stream().collect(Collectors.groupingBy(
                                sale -> {
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(sale.getCreatedAt());
                                    return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
                                },
                                Collectors.summingDouble(Sale::getTotalPrice)
                        )
                )
                .entrySet()
                .stream()
                .map(entry -> {
                    int day = Integer.parseInt(entry.getKey().split("-")[2]);
                    return new DailySales(entry.getKey(), entry.getValue(), day);
                })
                .sorted(Comparator.comparing(dailySale -> {
                    try {
                        return dateFormat.parse(dailySale.getDate());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .toList();



//      EXTRACT DAILY SALES COUNT REPORT
        List<DailySoldCount> dailySoldCounts = sales.stream().collect(Collectors.groupingBy(
                                sale -> {
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(sale.getCreatedAt());
                                    return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
                                },
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .map(entry -> {
                    int day = Integer.parseInt(entry.getKey().split("-")[2]);
                    return new DailySoldCount(entry.getKey(), entry.getValue(), day);
                })
                .sorted(Comparator.comparing(dailySale -> {
                    try {
                        return dateFormat.parse(dailySale.getDate());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .toList();


//      EXTRACT DAILY VIEWS REPORT
        List<DailyViews> dailyViews = dailyViewRepository.findAllByProduct_Owner_UsernameAndViewDateAfter(
                username, localStartDate)
                .stream()
                .collect(Collectors.groupingBy(
                        dailyView -> {
//                            Calendar cal = Calendar.getInstance();
//                            cal.setTime(dailyView.getViewDate());
//                            return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
                            return dailyView.getViewDate().getYear() + "-" + dailyView.getViewDate().getMonthValue() + "-" + dailyView.getViewDate().getDayOfMonth();
                        },
                        Collectors.summingInt(DailyView::getViewCount)
                ))
                .entrySet()
                .stream()
                .map(
                        entry -> {
                            int day = Integer.parseInt(entry.getKey().split("-")[2]);
                            return DailyViews.builder()
                                    .day(day)
                                    .date(entry.getKey())
                                    .viewsCount(entry.getValue())
                                    .build();
                        }
                )
                .sorted(
                        Comparator.comparing(
                                dv -> {
                                    try {
                                        return dateFormat.parse(dv.getDate());
                                    } catch (ParseException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        )
                )
                .toList();



        return Last7DaysSellerReport.builder()
                .dailySales(dailySales)
                .dailySoldCounts(dailySoldCounts)
                .dailyViews(dailyViews)
                .build();
    }

    @Override
    public AllSalesResponse getSalesByUsername(String username) {


        List<SaleVo> sales = saleRepository.findAllByOwner_Username(username)
                .stream()
                .map(
                        sale -> modelMapper.map(sale, SaleVo.class)
                )
//              Keep <SaleVo, Date> in case you want to use .reversed() right after comparing()
                .sorted(Comparator.<SaleVo, Date>comparing(saleVo -> {
                    try {
                        return dateFormat.parse(saleVo.getCreatedAt());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .toList();




        Integer totalViews = dailyViewRepository.findAllByProduct_Owner_Username(username)
                .stream()
                .map(
                        DailyView::getViewCount
                )
                .mapToInt(Integer::intValue)
                .sum();




        List<Double> totals = sales.stream().map(SaleVo::getTotalPrice).toList();
        List<Integer> quantities = sales.stream().map(SaleVo::getQuantity).toList();

        Integer grandQuantity = quantities.stream().mapToInt(Integer::intValue).sum();
        Double grandTotal = totals.stream().mapToDouble(Double::doubleValue).sum();

        return AllSalesResponse.builder()
                .sales(sales)
                .total(grandTotal)
                .quantityTotal(grandQuantity)
                .totalViews(totalViews)
                .build();
    }

    @Override
    public List<SaleVo> getSalesByUsernameAndDate(String username, String date) {

        Date trueDate = modelMapper.map(date, Date.class);

        return saleRepository.findAllByOwner_UsernameAndCreatedAt(username, trueDate)
                .stream()
                .map(
                        sale -> modelMapper.map(sale, SaleVo.class)
                )
                .sorted(Comparator.comparing(saleVo -> {
                    try {
                        return dateFormat.parse(saleVo.getCreatedAt());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .toList();
    }

}
