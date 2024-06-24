package personal.projects.TopShop.domaine.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserImageVo {

    private Long id;
    private String name;
    private String type;
    private String username;
    private String imageData;

}
