package personal.projects.TopShop.service.models;

import com.mysql.cj.MysqlType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.SQL;

import static com.mysql.cj.MysqlType.LONGBLOB;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    @Column(unique = true)
    private String username;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

}
