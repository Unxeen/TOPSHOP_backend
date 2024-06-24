package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import personal.projects.TopShop.service.models.DailyView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DailyViewRepository extends JpaRepository<DailyView, Long> {

    Optional<DailyView> findByProduct_IdAndViewDate(Long id, LocalDate viewDate);

    @Query("SELECT SUM(dv.viewCount) FROM DailyView dv WHERE dv.product.id = :productId")
    Integer findTotalViewsByProductId(@Param("productId") Long id);

    List<DailyView> findAllByProduct_Owner_Username(String owner);
    List<DailyView> findAllByProduct_Owner_UsernameAndViewDateAfter(String owner, LocalDate afterDate);

}
