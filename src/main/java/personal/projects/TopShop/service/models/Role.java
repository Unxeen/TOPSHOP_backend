package personal.projects.TopShop.service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String authority;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> authorities;


}
