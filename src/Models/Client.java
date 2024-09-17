package Models;

import Utilitaire.InputValidator;

import java.util.Date;
import java.util.List;

public class Client extends Personne {
    private String numSecuriteSocial;
    private Date dateNaissance;
    private Mutuelle mutuelle;
    private Medecin medecinTraitant;
    private List<Specialiste> specialistes;

    // Constructeur
    public Client(String nom, String prenom, String adresse, String codePostal, String ville, String telephone, String email, String numSecuriteSocial, Date dateNaissance, Mutuelle mutuelle, Medecin medecinTraitant, List<Specialiste> specialistes) {
        super(nom, prenom, adresse, codePostal, ville, telephone, email);
        setNumSecuriteSocial(numSecuriteSocial);
        this.dateNaissance = dateNaissance;
        this.mutuelle = mutuelle;
        this.medecinTraitant = medecinTraitant;
        this.specialistes = specialistes;
    }

    // Getters et setters
    public String getNumSecuriteSocial() {
        return numSecuriteSocial;
    }

    public void setNumSecuriteSocial(String numSecuriteSocial) {
        if (!InputValidator.isValidNumSecuriteSocial(numSecuriteSocial)) {
            throw new IllegalArgumentException("Numéro de sécurité sociale invalide");
        }
        this.numSecuriteSocial = numSecuriteSocial;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) {
        this.mutuelle = mutuelle;
    }

    public Medecin getMedecinTraitant() {
        return medecinTraitant;
    }

    public void setMedecinTraitant(Medecin medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public List<Specialiste> getSpecialistes() {
        return specialistes;
    }

    public void setSpecialistes(List<Specialiste> specialistes) {
        this.specialistes = specialistes;
    }
    @Override
    public String toString() {
        return super.toString() + " " + numSecuriteSocial;
    }
}
