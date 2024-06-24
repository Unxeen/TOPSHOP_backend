package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.projects.TopShop.service.models.Sale;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByOwner_Username(String username);
    List<Sale> findAllByOwner_UsernameAndCreatedAt(String username, Date date);
    List<Sale> findAllByOwner_UsernameAndCreatedAtBetween(String username, Date startDate, Date endDate);

}
