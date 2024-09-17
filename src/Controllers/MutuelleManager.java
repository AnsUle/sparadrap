package Controllers;

import Models.Mutuelle;
import java.util.ArrayList;
import java.util.List;

public class MutuelleManager {
    private List<Mutuelle> mutuelles;

    // Constructeur
    public MutuelleManager() {
        this.mutuelles = new ArrayList<>();
        initializeMutuelles();
    }

    // Initialiser les mutuelles
    private void initializeMutuelles() {
        Mutuelle mutuelle1 = new Mutuelle("Mutuelle A", "123 Rue de Paris", "75001", "Paris", "0142003004", "contact@mutuellea.fr", 75.0f);
        Mutuelle mutuelle2 = new Mutuelle("Mutuelle B", "456 Avenue de Lyon", "69002", "Lyon", "0478009005", "contact@mutuelleb.fr", 80.0f);
        Mutuelle mutuelle3 = new Mutuelle("Mutuelle C", "789 Boulevard de Marseille", "13003", "Marseille", "0491002006", "contact@mutuellec.fr", 85.0f);
        Mutuelle mutuelle4 = new Mutuelle("Mutuelle D", "101 Rue de Toulouse", "31000", "Toulouse", "0567008007", "contact@mutuelled.fr", 90.0f);

        mutuelles.add(mutuelle1);
        mutuelles.add(mutuelle2);
        mutuelles.add(mutuelle3);
        mutuelles.add(mutuelle4);
    }

    // Supprimer une mutuelle
    public void removeMutuelle(Mutuelle mutuelle) {
        mutuelles.remove(mutuelle);
    }
    public void  addMutuelle(Mutuelle mutuelle) {
        mutuelles.add(mutuelle);
    }

    public List<Mutuelle> getMutuelles() {
        return mutuelles;
    }

    // Trouver une mutuelle par nom
    public Mutuelle findMutuelleByName(String nom) {
        for (Mutuelle mutuelle : mutuelles) {
            if (mutuelle.getNom().equalsIgnoreCase(nom)) {
                return mutuelle;
            }
        }
        return null;
    }

    // Afficher toutes les mutuelles
    public void printMutuelles() {
        for (Mutuelle mutuelle : mutuelles) {
            System.out.println("Nom: " + mutuelle.getNom());
            System.out.println("Adresse: " + mutuelle.getAdresse());
            System.out.println("Code Postal: " + mutuelle.getCodePostal());
            System.out.println("Ville: " + mutuelle.getVille());
            System.out.println("Téléphone: " + mutuelle.getTelephone());
            System.out.println("Email: " + mutuelle.getEmail());
            System.out.println("Taux de Prise en Charge: " + mutuelle.getTauxPriseEnCharge());
            System.out.println("-------------------------------");
        }
    }
}
