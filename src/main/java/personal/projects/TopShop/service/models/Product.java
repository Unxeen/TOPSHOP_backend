package personal.projects.TopShop.service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String label;
    private String description;
    private Double price;
    private Integer stock;
    private Integer views;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductImage image;

    @ManyToOne
    private Client owner;

    @ManyToOne
    private Category category;

}
