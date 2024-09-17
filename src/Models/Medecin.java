package Models;

import Utilitaire.InputValidator;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

import java.util.List;

public class Medecin extends Personne {
    private String numAgrement;
    private List<Client> patients;

    // Constructeur
    public Medecin(String nom, String prenom, String adresse, String codePostal, String ville, String telephone, String email, String numAgrement) {
        super(nom, prenom, adresse, codePostal, ville, telephone, email);
        setNumAgrement(numAgrement);
        this.patients = new ArrayList<>();
    }

    // Getters et setters
    public String getNumAgrement() {
        return numAgrement;
    }

    public void setNumAgrement(String numAgrement) {
        if (!InputValidator.isValidNumAgrement(numAgrement)) {
            throw new IllegalArgumentException("Numéro d'agrément invalide");
        }
        this.numAgrement = numAgrement;
    }

    public List<Client> getPatients() {
        return patients;
    }

    public void setPatients(List<Client> patients) {
        this.patients = patients;
    }
    @Override
    public String toString() {
        return super.toString() + " " + "{" + "numAgrement=" + numAgrement + '}';
    }
}
