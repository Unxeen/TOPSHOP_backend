package personal.projects.TopShop.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import personal.projects.TopShop.service.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByLabelContainsIgnoreCaseAndPriceBetweenAndCategory_Name(String label, Double price1, Double price2, String name, Pageable pageable);
    List<Product> findAllByLabelContainsIgnoreCaseAndPriceBetween(String label, Double price1, Double price2, Pageable pageable);
    List<Product> findAllByPriceBetweenAndCategory_Name(Double price1, Double price2, String name, Pageable pageable);
    List<Product> findAllByPriceBetween(Double price1, Double price2, Pageable pageable);
    Optional<Product> findByLabelIgnoreCase(String label);
    List<Product> findAllByOwner_Username(String username);

}
