package web2.tp3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import web2.tp3.model.Nouvelle;

import java.util.Collection;

public interface NouvelleRepository extends JpaRepository<Nouvelle,Long> {
    Collection<Nouvelle> findByTitre(String nom);
}
