package personal.projects.TopShop.domaine.client;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateClientRequest {

    @Min(value = 5)
    private String username;
    private String firstname;
    private String lastname;
//    @Min(value = 5)
//    private String password;
    @Email
    private String email;
    private String postalAddress;
    @Nullable
    private MultipartFile image;
    private Boolean imageReset;

}
