package Controllers;

import Models.Ordonnance;
import Models.Client;
import Models.Medicament;
import Models.Medecin;
import Models.Specialiste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdonnanceManager {
    private List<Ordonnance> ordonnances;

    // Constructeur
    public OrdonnanceManager() {
        this.ordonnances = new ArrayList<>();
    }

    // Ajouter une ordonnance
    public void ajouterOrdonnance(Ordonnance ordonnance) {
        ordonnances.add(ordonnance);
    }

    // Supprimer une ordonnance
    public boolean supprimerOrdonnance(Ordonnance ordonnance) {
        return ordonnances.remove(ordonnance);
    }

    // Rechercher une ordonnance par patient
    public List<Ordonnance> rechercherParPatient(Client patient) {
        List<Ordonnance> result = new ArrayList<>();
        for (Ordonnance ordonnance : ordonnances) {
            if (ordonnance.getPatient().equals(patient)) {
                result.add(ordonnance);
            }
        }
        return result;
    }

    // Rechercher une ordonnance par médecin
    public List<Ordonnance> rechercherParMedecin(Medecin medecin) {
        List<Ordonnance> result = new ArrayList<>();
        for (Ordonnance ordonnance : ordonnances) {
            if (ordonnance.getMedecin().equals(medecin)) {
                result.add(ordonnance);
            }
        }
        return result;
    }

    // Rechercher une ordonnance par date
    public List<Ordonnance> rechercherParDate(Date date) {
        List<Ordonnance> result = new ArrayList<>();
        for (Ordonnance ordonnance : ordonnances) {
            if (ordonnance.getDate().equals(date)) {
                result.add(ordonnance);
            }
        }
        return result;
    }

    // Rechercher une ordonnance par spécialiste
    public List<Ordonnance> rechercherParSpecialiste(Specialiste specialiste) {
        List<Ordonnance> result = new ArrayList<>();
        for (Ordonnance ordonnance : ordonnances) {
            if (ordonnance.getSpecialiste() != null && ordonnance.getSpecialiste().equals(specialiste)) {
                result.add(ordonnance);
            }
        }
        return result;
    }

    // Obtenir toutes les ordonnances
    public List<Ordonnance> getToutesLesOrdonnances() {
        return new ArrayList<>(ordonnances);
    }
}
