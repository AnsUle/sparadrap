package Models;

import Utilitaire.InputValidator;

public abstract class Personne {
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;

    // Constructeur
    public Personne(String nom, String prenom, String adresse, String codePostal, String ville, String telephone, String email) {
        setNom(nom);
        setPrenom(prenom);
        setAdresse(adresse);
        setCodePostal(codePostal);
        setVille(ville);
        setTelephone(telephone);
        setEmail(email);
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (!InputValidator.isValidName(nom)) {
            throw new IllegalArgumentException("Nom invalide");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (!InputValidator.isValidPrenom(prenom)) {
            throw new IllegalArgumentException("Prénom invalide");
        }
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        if (!InputValidator.isValidAdresse(adresse)) {
            throw new IllegalArgumentException("Adresse invalide");
        }
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        if (!InputValidator.isValidCodePostal(codePostal)) {
            throw new IllegalArgumentException("Code postal invalide");
        }
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        if (!InputValidator.isValidVille(ville)) {
            throw new IllegalArgumentException("Ville invalide");
        }
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if (!InputValidator.isValidTelephone(telephone)) {
            throw new IllegalArgumentException("Numéro de téléphone invalide");
        }
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!InputValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Adresse email invalide");
        }
        this.email = email;
    }
    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
