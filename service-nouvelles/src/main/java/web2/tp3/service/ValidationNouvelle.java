package web2.tp3.service;

public class ValidationNouvelle {

    public String validerNouvelle(String titre, String src, String resume, String texte) {
        //Validation taille du titre
        if (titre.length() < 4 || titre.length() > 75) {
            return ("Le titre doit contenir entre 4 et 75 caracteres! "+ titre.length() + " caracteres présent pour le titre de la nouvelle: " + titre);
        }
        //Validation source image
        if (src.isEmpty()) {
            return ("La source de l'image ne peut pas être vide! Pour la nouvelle: " + titre);
        }

        //Validation taille resume
        if (resume.isEmpty()) {
            return "Le resume ne peut pas être vide! Dans la nouvelle: " + titre;
        }
        if (resume.length() > 300) {
            return "Le resume doit contenir au maxium 300 caracteres! " + resume.length() + " caracteres present pour la nouvelle:" + titre;
        }
        //Validation taille texte
        if (texte.isEmpty()) {
            return "Le texte de la nouvelle ne peut pas être vide!";
        }
        if (texte.length() > 250) {
            return "Le texte doit contenir au maxium 150 caracteres! " + texte.length() + " caracteres present pour la nouvelle:" + titre;
        }

        return "";
    }

}
