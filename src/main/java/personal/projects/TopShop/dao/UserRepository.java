package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.projects.TopShop.service.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
