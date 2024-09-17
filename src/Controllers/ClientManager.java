package Controllers;

import Models.Client;
import Models.Medecin;
import Models.Mutuelle;
import Models.Specialiste;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientManager {
    private List<Client> clients;
    private MedecinManager medecinManager;
    private SpecialisteManager specialisteManager;
    private MutuelleManager mutuelleManager;

    // Constructeur
    public ClientManager(MedecinManager medecinManager, SpecialisteManager specialisteManager, MutuelleManager mutuelleManager) {
        this.clients = new ArrayList<>();
        this.medecinManager = medecinManager;
        this.specialisteManager = specialisteManager;
        this.mutuelleManager = mutuelleManager;
        initializeClients();
    }

    // Méthode pour initialiser des clients
    private void initializeClients() {
        Mutuelle mutuelle1 = mutuelleManager.findMutuelleByName("Mutuelle A");
        Mutuelle mutuelle2 = mutuelleManager.findMutuelleByName("Mutuelle B");

        Medecin medecin1 = medecinManager.trouverMedecinParNumAgrement("A12345");
        Medecin medecin2 = medecinManager.trouverMedecinParNumAgrement("B67890");

        List<Specialiste> specialistes1 = new ArrayList<>();
        //specialistes1.add(specialisteManager.findSpecialisteByName("Dr. Roche"));

        List<Specialiste> specialistes2 = new ArrayList<>();
        //specialistes2.add(specialisteManager.findSpecialisteByName("Dr. Lefevre"));

        Client client1 = new Client("Durand", "Paul", "123 Rue des Clients", "75001", "Paris", "0142003000", "paul.durand@example.com", "1234567890", new Date(), mutuelle1, medecin1, specialistes1);
        Client client2 = new Client("Martin", "Sophie", "456 Avenue des Clients", "69002", "Lyon", "0478009000", "sophie.martin@example.com", "0987654321", new Date(), mutuelle2, medecin2, specialistes2);

        clients.add(client1);
        clients.add(client2);
    }

    // Méthode pour ajouter un client
    public void ajouterClient(Client client) {
        clients.add(client);
        System.out.println("Client ajouté : " + client.getNom() + " " + client.getPrenom());
    }

    // Méthode pour supprimer un client
    public void supprimerClient(Client client) {
        clients.remove(client);
        System.out.println("Client supprimé : " + client.getNom() + " " + client.getPrenom());
    }

    // Méthode pour récupérer tous les clients
    public List<Client> getClients() {
        return clients;
    }

    // Méthode pour trouver un client par son numéro de sécurité sociale
    public Client trouverClientParNumSecuriteSocial(String numSecuriteSocial) {
        for (Client client : clients) {
            if (client.getNumSecuriteSocial().equals(numSecuriteSocial)) {
                return client;
            }
        }
        return null; // Retourne null si aucun client n'est trouvé
    }

    // Méthode pour afficher tous les clients
    public void afficherClients() {
        for (Client client : clients) {
            System.out.println("Nom: " + client.getNom());
            System.out.println("Prénom: " + client.getPrenom());
            System.out.println("Adresse: " + client.getAdresse());
            System.out.println("Code Postal: " + client.getCodePostal());
            System.out.println("Ville: " + client.getVille());
            System.out.println("Téléphone: " + client.getTelephone());
            System.out.println("Email: " + client.getEmail());
            System.out.println("Numéro de Sécurité Sociale: " + client.getNumSecuriteSocial());
            System.out.println("Date de Naissance: " + client.getDateNaissance());
            System.out.println("Mutuelle: " + (client.getMutuelle() != null ? client.getMutuelle().getNom() : "Aucune"));
            System.out.println("Médecin Traitement: " + (client.getMedecinTraitant() != null ? client.getMedecinTraitant().getNom() : "Aucun"));
            System.out.println("Spécialistes: ");
            if (client.getSpecialistes() != null && !client.getSpecialistes().isEmpty()) {
                for (Specialiste specialiste : client.getSpecialistes()) {
                    System.out.println("  - " + specialiste.getNom() + " " + specialiste.getPrenom() + " (" + specialiste.getSpecialite() + ")");
                }
            } else {
                System.out.println("  Aucun");
            }
            System.out.println("-------------------------------");
        }
    }
}
