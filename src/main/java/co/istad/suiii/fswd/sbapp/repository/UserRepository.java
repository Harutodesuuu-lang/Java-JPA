package co.istad.suiii.fswd.sbapp.repository;

import co.istad.suiii.fswd.sbapp.security.model.CustomUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CustomUserDetail, Integer> {
    Optional<CustomUserDetail> findByUsername(String username);
}
