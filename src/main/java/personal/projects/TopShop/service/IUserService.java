package personal.projects.TopShop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import personal.projects.TopShop.domaine.user.PermissionVo;
import personal.projects.TopShop.domaine.user.RoleVo;
import personal.projects.TopShop.service.models.Permission;
import personal.projects.TopShop.service.models.Role;

public interface IUserService extends UserDetailsService {

    RoleVo getRoleByAuthority(String authority);
    PermissionVo getPermissionByAuthority(String authority);
    RoleVo saveRole(RoleVo roleVo);
    PermissionVo savePermission(PermissionVo permissionVo);

}
