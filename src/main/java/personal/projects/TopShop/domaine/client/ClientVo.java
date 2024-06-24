package personal.projects.TopShop.domaine.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.projects.TopShop.domaine.user.UserImageVo;
import personal.projects.TopShop.service.models.UserImage;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientVo {

    private String identityRef;
    private String username;
    private String email;
    private String postalAddress;
    private String firstname;
    private String lastname;
    private UserImageVo image;

}
