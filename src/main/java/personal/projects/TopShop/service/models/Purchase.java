package personal.projects.TopShop.service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Client client;

    private Double totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //    @OneToMany
    //    private List<LineProduct> items;

}
