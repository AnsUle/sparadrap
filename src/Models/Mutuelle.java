package Models;
import Utilitaire.InputValidator;

public class Mutuelle {
    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;
    private float tauxPriseEnCharge;

    // Constructeur
    public Mutuelle(String nom, String adresse, String codePostal, String ville, String telephone, String email, float tauxPriseEnCharge) {
        setNom(nom);
        setAdresse(adresse);
        setCodePostal(codePostal);
        setVille(ville);
        setTelephone(telephone);
        setEmail(email);
        setTauxPriseEnCharge(tauxPriseEnCharge);
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (!InputValidator.isValidName(nom)) {
            throw new IllegalArgumentException("Nom de mutuelle invalide");
        }
        this.nom = nom;
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

    public float getTauxPriseEnCharge() {
        return tauxPriseEnCharge;
    }

    public void setTauxPriseEnCharge(float tauxPriseEnCharge) {
        if (!InputValidator.isValidTauxPriseEnCharge(tauxPriseEnCharge)) {
            throw new IllegalArgumentException("Taux de prise en charge invalide");
        }
        this.tauxPriseEnCharge = tauxPriseEnCharge;
    }
    @Override
    public String toString() {
        return nom + " " + ville + " " + "txPriseEnCharge : " + tauxPriseEnCharge;
    }
}
