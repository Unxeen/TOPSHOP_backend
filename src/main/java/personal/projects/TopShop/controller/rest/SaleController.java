package personal.projects.TopShop.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import personal.projects.TopShop.domaine.sale.AllSalesResponse;
import personal.projects.TopShop.domaine.sale.DailySales;
import personal.projects.TopShop.domaine.sale.Last7DaysSellerReport;
import personal.projects.TopShop.domaine.sale.SaleVo;
import personal.projects.TopShop.service.SaleServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sales")
public class SaleController {

    private SaleServiceImpl saleService;

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'SUPPORT')")
    @GetMapping("/weekly/{username}")
    public ResponseEntity<Last7DaysSellerReport> getWeeklySummary(@PathVariable("username") String username){

        return new ResponseEntity<>(
                saleService.getSalesForLast7DaysByUsername(username),
                HttpStatus.OK
        );
    }


    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'SUPPORT')")
    @GetMapping("/{username}")
    public ResponseEntity<AllSalesResponse> getAll(@PathVariable("username") String username){

        return new ResponseEntity<>(
                saleService.getSalesByUsername(username),
                HttpStatus.OK
        );
    }


    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'SUPPORT')")
    @GetMapping("/byDate/{username}")
    public ResponseEntity<List<SaleVo>> getAllByDate(@PathVariable("username") String username,
                                                     @RequestParam("date") String date){

        return new ResponseEntity<>(
                saleService.getSalesByUsernameAndDate(username, date),
                HttpStatus.OK
        );
    }

}
