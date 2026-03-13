package co.istad.suiii.fswd.sbapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted;

    // Relationship here
    // MappedBy, tells hibernate category relationship
    // already establish in product entity
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}