package personal.projects.TopShop.domaine.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo implements GrantedAuthority {

    private String authority;
    private List<PermissionVo> authorities;

}
