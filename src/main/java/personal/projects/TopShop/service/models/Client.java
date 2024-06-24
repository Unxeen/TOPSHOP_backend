package personal.projects.TopShop.service.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Client extends User{

    private String identityRef;


//    @OneToMany(mappedBy = "client")
//    private List<LineProduct> lineProducts;
//
//
//    @OneToMany(mappedBy = "owner")
//    private List<Sale> sales1;
//
//    @OneToMany(mappedBy = "buyer")
//    private List<Sale> sales;

}
