package personal.projects.TopShop.service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

    private Double totalPrice;
    private Integer quantity;

    @ManyToOne
    private Client owner;

    @ManyToOne
    private Client buyer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}
