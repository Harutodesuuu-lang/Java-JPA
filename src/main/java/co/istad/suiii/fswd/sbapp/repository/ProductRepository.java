package co.istad.suiii.fswd.sbapp.repository;

import co.istad.suiii.fswd.sbapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    Product findByCode(String code);
}
