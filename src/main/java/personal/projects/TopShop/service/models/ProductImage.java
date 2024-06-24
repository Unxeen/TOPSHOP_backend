package personal.projects.TopShop.service.models;

import com.mysql.cj.MysqlType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.mysql.cj.MysqlType.LONGBLOB;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

//    @OneToOne(mappedBy = "image")
//    private Product product;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String imageData;
}
