package Utilitaire;

import java.util.regex.Pattern;

public class InputValidator {

    // Méthode pour valider un nom
    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return true;//Pattern.matches("[a-zA-Z]+", name);
    }

    // Méthode pour valider un prénom
    public static boolean isValidPrenom(String prenom) {
        if (prenom == null || prenom.isEmpty()) {
            return false;
        }
        return Pattern.matches("[a-zA-Z]+", prenom);
    }

    // Méthode pour valider une adresse
    public static boolean isValidAdresse(String adresse) {
        if (adresse == null || adresse.isEmpty()) {
            return false;
        }
        return true;//Pattern.matches("[a-zA-Z0-9\\s,.-]+", adresse);
    }

    // Méthode pour valider un code postal
    public static boolean isValidCodePostal(String codePostal) {
        if (codePostal == null || codePostal.isEmpty()) {
            return false;
        }
        return Pattern.matches("\\d{5}", codePostal);
    }

    // Méthode pour valider une ville
    public static boolean isValidVille(String ville) {
        if (ville == null || ville.isEmpty()) {
            return false;
        }
        return Pattern.matches("[a-zA-Z\\s-]+", ville);
    }

    // Méthode pour valider un numéro de téléphone
    public static boolean isValidTelephone(String telephone) {
        if (telephone == null || telephone.isEmpty()) {
            return false;
        }
        return Pattern.matches("\\d{10}", telephone);
    }

    // Méthode pour valider une adresse email
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email);
    }

    // Méthode pour valider un numéro de sécurité sociale
    public static boolean isValidNumSecuriteSocial(String numSecuriteSocial) {
        if (numSecuriteSocial == null || numSecuriteSocial.isEmpty()) {
            return false;
        }
        return true;
    }

    // Méthode pour valider un numéro d'agrément
    public static boolean isValidNumAgrement(String numAgrement) {
        if (numAgrement == null || numAgrement.isEmpty()) {
            return false;
        }
        return true;//Pattern.matches("\\d+", numAgrement);
    }

    // Méthode pour valider une spécialité
    public static boolean isValidSpecialite(String specialite) {
        if (specialite == null || specialite.isEmpty()) {
            return false;
        }
        return true;//Pattern.matches("[a-zA-Z\\s-]+", specialite);
    }

    // Méthode pour valider une catégorie de médicament
    public static boolean isValidCategorie(String categorie) {
        if (categorie == null || categorie.isEmpty()) {
            return false;
        }
        return Pattern.matches("[a-zA-Z\\s-]+", categorie);
    }

    // Méthode pour valider un prix
    public static boolean isValidPrix(float prix) {
        return prix >= 0;
    }

    // Méthode pour valider une quantité
    public static boolean isValidQuantite(int quantite) {
        return quantite >= 0;
    }

    // Méthode pour valider un taux de prise en charge
    public static boolean isValidTauxPriseEnCharge(float tauxPriseEnCharge) {
        return tauxPriseEnCharge >= 0 && tauxPriseEnCharge <= 100;
    }
}
