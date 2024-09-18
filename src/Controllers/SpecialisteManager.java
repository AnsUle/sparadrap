package Controllers;

import Models.Client;
import Models.Specialiste;
import java.util.ArrayList;
import java.util.List;

public class SpecialisteManager {
    private List<Specialiste> specialistes;

    // Constructeur
    public SpecialisteManager() {
        this.specialistes = new ArrayList<>();
        initializeSpecialistes();
    }

    // Initialiser les spécialistes
    private void initializeSpecialistes() {
        Specialiste specialiste1 = new Specialiste("Roche", "Alice", "123 Rue de la Médecine", "75001", "Paris", "0142003006", "dr.roche@example.com", "Cardiologie");
        Specialiste specialiste2 = new Specialiste("Lefevre", "Julien", "456 Avenue des Médecins", "69002", "Lyon", "0478009008", "dr.lefevre@example.com", "Dermatologie");
        Specialiste specialiste3 = new Specialiste("Bernard", "Sophie", "789 Boulevard de la Santé", "13003", "Marseille", "0491002009", "dr.bernard@example.com", "Neurologie");
        Specialiste specialiste4 = new Specialiste("Petit", "Paul", "101 Rue des Spécialistes", "31000", "Toulouse", "0567008010", "dr.petit@example.com", "Orthopédie");

        // Ajout des spécialistes à la liste
        specialistes.add(specialiste1);
        specialistes.add(specialiste2);
        specialistes.add(specialiste3);
        specialistes.add(specialiste4);
    }

    // Ajouter un spécialiste
    public void addSpecialiste(Specialiste specialiste) {
        specialistes.add(specialiste);
    }

    // Supprimer un spécialiste
    public void removeSpecialiste(Specialiste specialiste) {
        specialistes.remove(specialiste);
    }

    // Méthode pour récupérer tous les clients
    public List<Specialiste> getSpecialistes() {
        return specialistes;
    }

    // Trouver un spécialiste par nom
    public Specialiste findSpecialisteByName(String nom) {
        for (Specialiste specialiste : specialistes) {
            if (specialiste.getNom().equalsIgnoreCase(nom)) {
                return specialiste;
            }
        }
        return null;
    }

    // Afficher tous les spécialistes
    public void printSpecialistes() {
        for (Specialiste specialiste : specialistes) {
            System.out.println("Nom: " + specialiste.getNom());
            System.out.println("Prénom: " + specialiste.getPrenom());
            System.out.println("Adresse: " + specialiste.getAdresse());
            System.out.println("Code Postal: " + specialiste.getCodePostal());
            System.out.println("Ville: " + specialiste.getVille());
            System.out.println("Téléphone: " + specialiste.getTelephone());
            System.out.println("Email: " + specialiste.getEmail());
            System.out.println("Spécialité: " + specialiste.getSpecialite());
            System.out.println("-------------------------------");
        }
    }
}

