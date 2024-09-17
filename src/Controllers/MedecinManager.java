package Controllers;

import Models.Medecin;
import java.util.ArrayList;
import java.util.List;
import Controllers.ClientManager;
import Models.Specialiste;

public class MedecinManager {
    private List<Medecin> medecins;

    // Constructeur
    public MedecinManager() {
        this.medecins = new ArrayList<>();
        initializeMedecins();
    }

    // Méthode pour initialiser des médecins
    private void initializeMedecins() {
        // Création de quelques médecins pour démonstration
        Medecin medecin1 = new Medecin("Dupont", "Marc", "123 Rue de Médecine", "75001", "Paris", "0142003007", "dr.dupont@medecin.fr", "A12345");
        Medecin medecin2 = new Medecin("Lefebvre", "Claire", "456 Avenue des Soins", "69002", "Lyon", "0478009009", "dr.lefebvre@medecin.fr", "B67890");
        Medecin medecin3 = new Medecin("Martin", "Jean", "789 Boulevard de la Santé", "13003", "Marseille", "0491002010", "dr.martin@medecin.fr", "C11223");

        // Ajout des médecins à la liste
        medecins.add(medecin1);
        medecins.add(medecin2);
        medecins.add(medecin3);
    }

    // Méthode pour ajouter un médecin
    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
        System.out.println("Médecin ajouté : " + medecin.getNom() + " " + medecin.getPrenom());
    }

    // Méthode pour supprimer un médecin
    public void supprimerMedecin(Medecin medecin) {
        medecins.remove(medecin);
        System.out.println("Médecin supprimé : " + medecin.getNom() + " " + medecin.getPrenom());
    }

    // Méthode pour récupérer tous les médecins
    public List<Medecin> getTousLesMedecins() {
        return medecins;
    }

    // Méthode pour trouver un médecin par son numéro d'agrément
    public Medecin trouverMedecinParNumAgrement(String numAgrement) {
        for (Medecin medecin : medecins) {
            if (medecin.getNumAgrement().equals(numAgrement)) {
                return medecin;
            }
        }
        return null; // Retourne null si aucun médecin n'est trouvé
    }

    // Trouver un spécialiste par nom
    public Medecin findMedecinByName(String nom) {
        for (Medecin medecin : medecins) {
            if (medecin.getNom().equalsIgnoreCase(nom)) {
                return medecin;
            }
        }
        return null;
    }

    // Méthode pour afficher tous les médecins
    public void afficherMedecins() {
        for (Medecin medecin : medecins) {
            System.out.println("Nom: " + medecin.getNom());
            System.out.println("Prénom: " + medecin.getPrenom());
            System.out.println("Adresse: " + medecin.getAdresse());
            System.out.println("Code Postal: " + medecin.getCodePostal());
            System.out.println("Ville: " + medecin.getVille());
            System.out.println("Téléphone: " + medecin.getTelephone());
            System.out.println("Email: " + medecin.getEmail());
            System.out.println("Numéro d'Agrément: " + medecin.getNumAgrement());
            System.out.println("-------------------------------");
        }
    }
}
