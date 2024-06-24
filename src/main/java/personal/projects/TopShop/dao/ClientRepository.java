package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.projects.TopShop.service.models.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByIdentityRef(String identityRef);
    Optional<Client> findByUsername(String username);

}
