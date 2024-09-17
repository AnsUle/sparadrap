package Controllers;

import Models.Ordonnance;
import Models.Client;
import Models.Medecin;
import Models.Medicament;
import Models.Specialiste;

import java.util.Date;
import java.util.List;

public class OrdonnanceController {

    private Ordonnance ordonnance;

    // Constructeur pour initialiser le contrôleur sans ordonnance
    public OrdonnanceController() {
    }

    // Méthode pour créer une nouvelle ordonnance
    public void creerOrdonnance(Date date, Medecin medecin, Client patient, List<Medicament> medicaments, Specialiste specialiste) {
        this.ordonnance = new Ordonnance(date, medecin, patient, medicaments, specialiste);
        System.out.println("Ordonnance créée avec succès.");
    }

    // Méthode pour obtenir l'ordonnance actuelle
    public Ordonnance getOrdonnance() {
        if (ordonnance != null) {
            return ordonnance;
        } else {
            System.out.println("Aucune ordonnance n'a été créée.");
            return null;
        }
    }

    // Mettre à jour les détails de l'ordonnance
    public void mettreAJourOrdonnance(Date date, Medecin medecin, Client patient, List<Medicament> medicaments, Specialiste specialiste) {
        if (ordonnance != null) {
            ordonnance.setDate(date);
            ordonnance.setMedecin(medecin);
            ordonnance.setPatient(patient);
            ordonnance.setMedicaments(medicaments);
            ordonnance.setSpecialiste(specialiste);
            System.out.println("Ordonnance mise à jour avec succès.");
        } else {
            System.out.println("Aucune ordonnance à mettre à jour.");
        }
    }

    // Méthode pour afficher les détails de l'ordonnance
    public void afficherOrdonnance() {
        if (ordonnance != null) {
            System.out.println("Détails de l'ordonnance : ");
            System.out.println("Date : " + ordonnance.getDate());
            System.out.println("Médecin : " + ordonnance.getMedecin().getNom());
            System.out.println("Patient : " + ordonnance.getPatient().getNom());
            System.out.println("Médicaments : ");
            for (Medicament medicament : ordonnance.getMedicaments()) {
                System.out.println("- " + medicament.getNom());
            }
            System.out.println("Spécialiste : " + ordonnance.getSpecialiste().getNom());
        } else {
            System.out.println("Aucune ordonnance à afficher.");
        }
    }

    // Méthode pour supprimer l'ordonnance
    public void supprimerOrdonnance() {
        if (ordonnance != null) {
            ordonnance = null;
            System.out.println("Ordonnance supprimée avec succès.");
        } else {
            System.out.println("Aucune ordonnance à supprimer.");
        }
    }
}

