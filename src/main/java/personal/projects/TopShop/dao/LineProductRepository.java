package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import personal.projects.TopShop.service.models.LineProduct;

import java.util.List;
import java.util.Optional;

public interface LineProductRepository extends JpaRepository<LineProduct, Long> {
    @Query("SELECT lp FROM LineProduct lp WHERE lp.client.username = :username AND lp.product.stock > 0")
    List<LineProduct> findAllByClient_Username(@Param("username") String username);
    Optional<LineProduct> findByProduct_IdAndClient_Username(
            Long id,
            String username);
}
