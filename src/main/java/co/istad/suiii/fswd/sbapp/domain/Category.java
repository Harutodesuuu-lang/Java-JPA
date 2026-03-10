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
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity is auto increment
    private Integer id;
    private String name;
@OneToMany(mappedBy = "category")
    private List<Product> products;

}
