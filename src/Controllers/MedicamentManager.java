package Controllers;

import Models.Medicament;
import Models.Mutuelle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicamentManager {
    private List<Medicament> medicaments;
    private static MedicamentManager instance;

    // Constructeur
    public MedicamentManager() {
        this.medicaments = new ArrayList<>();
        initializeMedicaments(); // Initialiser les médicaments lors de la création du manager
    }
    public static MedicamentManager getInstance() {
        if (instance == null) {
            instance = new MedicamentManager();
        }
        return instance;
    }
    // Méthode pour initialiser des médicaments
    private void initializeMedicaments() {
        // Création de quelques médicaments pour démonstration
        Medicament medicament1 = new Medicament("Paracétamol", "Analgesique", 2.5f, new Date(), 100);
        Medicament medicament2 = new Medicament("Ibuprofène", "Anti-inflammatoire", 5.0f, new Date(), 50);
        Medicament medicament3 = new Medicament("Amoxicilline", "Antibiotique", 7.0f, new Date(), 30);

        // Ajout des médicaments à la liste
        medicaments.add(medicament1);
        medicaments.add(medicament2);
        medicaments.add(medicament3);
    }


    // Méthode pour ajouter un médicament
    public void ajouterMedicament(Medicament medicament) {
        medicaments.add(medicament);
    }

    // Méthode pour modifier un médicament
    public boolean modifierMedicament(Medicament medicament, String nouveauNom, String nouvelleCategorie, float nouveauPrix, int nouvelleQuantite) {
        if (medicaments.contains(medicament)) {
            medicament.setNom(nouveauNom);
            medicament.setCategorie(nouvelleCategorie);
            medicament.setPrix(nouveauPrix);
            medicament.setQuantite(nouvelleQuantite);
            return true;
        } else {
            return false;
        }
    }

    // Méthode pour supprimer un médicament
    public boolean supprimerMedicament(Medicament medicament) {
        return medicaments.remove(medicament);
    }

    public void  addMedicament (Medicament medicament) {
       medicaments.add(medicament);
    }

    // Méthode pour récupérer tous les médicaments
    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    // Méthode pour afficher tous les médicaments
    public void afficherTousLesMedicaments() {
        for (Medicament medicament : medicaments) {
            System.out.println("- " + medicament.getNom() + " | Catégorie: " + medicament.getCategorie() + " | Prix: " + medicament.getPrix() + " | Quantité: " + medicament.getQuantite());
        }
    }

    // Méthode pour trouver un médicament par son nom
    public Medicament trouverMedicamentParNom(String nom) {
        for (Medicament medicament : medicaments) {
            if (medicament.getNom().equalsIgnoreCase(nom)) {
                return medicament;
            }
        }
        return null;
    }
}
