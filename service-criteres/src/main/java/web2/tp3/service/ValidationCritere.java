//package web2.tp3.service;
//
//import web2.tp3.model.Critere;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ValidationCritere {
//
//    public String valider(Critere critere) {
//
//        if (critere == null) {
//            return "Le critère est nul.";
//        }
//
//        // Vérifie que le type n'est pas vide
//        if (critere.getType() == null) {
//            return "Le type du critère est obligatoire.";
//        }
//
//        // Vérifie que l'utilisateur est renseigné
//        if (critere.getIdUtilisateur() == null || critere.getIdUtilisateur() <= 0) {
//            return "L'identifiant de l'utilisateur est invalide.";
//        }
//        return "";
//    }
//}
