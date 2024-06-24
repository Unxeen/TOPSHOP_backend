package personal.projects.TopShop.domaine.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo implements UserDetails {

    private Long id;
    private List<RoleVo> authorities = new ArrayList<>();
    @NotEmpty
    private String password;
    @NotEmpty
    private String username;
    private String email;
    private String postalAddress;
    private UserImageVo image;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}
