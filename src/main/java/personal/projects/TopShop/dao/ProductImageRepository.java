package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.projects.TopShop.service.models.ProductImage;

import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> getByName(String name);
}
