package co.istad.suiii.fswd.sbapp.repository;


import co.istad.suiii.fswd.sbapp.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findByIsDeletedFalseOrderByOrderedAtDesc(Pageable pageable);

}