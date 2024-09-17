package Models;

import Utilitaire.InputValidator;

public class Specialiste extends Personne {
    private String specialite;

    // Constructeur
    public Specialiste(String nom, String prenom, String adresse, String codePostal, String ville, String telephone, String email, String specialite) {
        super(nom, prenom, adresse, codePostal, ville, telephone, email);
        setSpecialite(specialite);
    }

    // Getters et setters
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        if (!InputValidator.isValidSpecialite(specialite)) {
            throw new IllegalArgumentException("Spécialité invalide");
        }
        this.specialite = specialite;
    }
}
