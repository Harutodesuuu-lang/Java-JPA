package co.istad.suiii.fswd.sbapp.repository;

import co.istad.suiii.fswd.sbapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
