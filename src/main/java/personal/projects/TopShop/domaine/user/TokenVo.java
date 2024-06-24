package personal.projects.TopShop.domaine.user;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TokenVo {
    private String jwtToken;
    private String username;
    private List<String> roles = new ArrayList<>();
}
