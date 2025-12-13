package web2.tp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web2.tp3.model.Critere;
import java.util.List;

public interface CritereRepository extends JpaRepository<Critere, Long> {
    List<Critere> findByIdUtilisateur(Long idUtilisateur);
}