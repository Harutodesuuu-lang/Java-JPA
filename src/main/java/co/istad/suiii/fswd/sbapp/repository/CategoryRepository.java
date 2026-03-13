package co.istad.suiii.fswd.sbapp.repository;

import co.istad.suiii.fswd.sbapp.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
