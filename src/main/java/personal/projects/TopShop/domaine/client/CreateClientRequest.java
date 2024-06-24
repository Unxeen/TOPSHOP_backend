package personal.projects.TopShop.domaine.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateClientRequest {

    @Min(value = 5)
    private String username;
    private String firstname;
    private String lastname;
    @Pattern(regexp = "^/(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}/$")
    private String password;
    @Email
    private String email;
    private String postalAddress;

}
