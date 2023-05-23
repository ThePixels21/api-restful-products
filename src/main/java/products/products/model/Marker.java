package products.products.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marker",
indexes = {@Index(name = "index_name",columnList = "name",unique = true)})
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
