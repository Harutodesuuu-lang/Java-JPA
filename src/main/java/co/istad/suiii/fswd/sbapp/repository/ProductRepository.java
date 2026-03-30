package co.istad.suiii.fswd.sbapp.repository;

import co.istad.suiii.fswd.sbapp.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {
    Product findByCode(String code);

    Page<Product> findByIsAvailableTrue(Pageable pageable);

}
