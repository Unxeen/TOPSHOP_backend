package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.projects.TopShop.service.models.Support;

import java.util.Optional;

public interface SupportRepository extends JpaRepository<Support, Long> {

    Optional<Support> findByIdentityRef(String identityRef);
    Optional<Support> findByUsername(String username);

}
