package personal.projects.TopShop.domaine.support;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSupportRequest {

    @Min(value = 5)
    private String username;
    private String firstname;
    private String lastname;
    @Min(value = 5)
    private String password;
    @Email
    private String email;
    private String postalAddress;
    private Date birthDay;

}
