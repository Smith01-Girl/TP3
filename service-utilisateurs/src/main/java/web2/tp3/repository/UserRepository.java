package web2.tp3.repository;

import web2.tp3.model.User;
import web2.tp3.model.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findUserByRole(UserRole role);
    boolean existsByEmail(String email);
    Long countByRole(UserRole role);
}
