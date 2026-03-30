package co.istad.suiii.fswd.sbapp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

// Make POJO (Plain Old Java Object)
@Getter
@Setter
@NoArgsConstructor
// Make JPA Entity
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String address;

    @Column(nullable = false)
    private String orderedBy;

    @Column(nullable = false)
    private Instant orderedAt;

    @Column(nullable = false)
    private Boolean isDeleted;
    @JsonManagedReference
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;
}