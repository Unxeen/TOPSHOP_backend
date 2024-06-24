package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.projects.TopShop.service.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
}
