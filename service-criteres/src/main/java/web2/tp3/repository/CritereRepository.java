//package web2.tp3.repository;
//
//import web2.tp3.model.Critere;
//import web2.tp3.model.TypeCritere;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Collection;
//
//public interface CritereRepository extends JpaRepository<Critere, Long> {
//    Collection<Critere> findByType(TypeCritere type);
//    Collection<Critere> findByIdUtilisateur(Long idUtilisateur);
//    void deleteByIdUtilisateur(Long idUtilisateur);
//
//}
