package Models;

import Utilitaire.InputValidator;

import java.util.Date;

public class Medicament {
    private String nom;
    private String categorie;
    private float prix;
    private Date dateMiseEnService;
    private int quantite;

    // Constructeur
    public Medicament(String nom, String categorie, float prix, Date dateMiseEnService, int quantite) {
        setNom(nom);
        setCategorie(categorie);
        setPrix(prix);
        this.dateMiseEnService = dateMiseEnService;
        setQuantite(quantite);
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (!InputValidator.isValidName(nom)) {
            throw new IllegalArgumentException("Nom de médicament invalide");
        }
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        if (!InputValidator.isValidCategorie(categorie)) {
            throw new IllegalArgumentException("Catégorie de médicament invalide");
        }
        this.categorie = categorie;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        if (!InputValidator.isValidPrix(prix)) {
            throw new IllegalArgumentException("Prix invalide");
        }
        this.prix = prix;
    }

    public Date getDateMiseEnService() {
        return dateMiseEnService;
    }

    public void setDateMiseEnService(Date dateMiseEnService) {
        this.dateMiseEnService = dateMiseEnService;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        if (!InputValidator.isValidQuantite(quantite)) {
            throw new IllegalArgumentException("Quantité invalide");
        }
        this.quantite = quantite;
    }
    @Override
    public String toString() {
        return nom + " " + quantite  + " " + prix;
    }
}
