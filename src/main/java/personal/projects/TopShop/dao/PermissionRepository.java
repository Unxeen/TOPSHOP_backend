package personal.projects.TopShop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.projects.TopShop.service.models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByAuthority(String authority);
}
