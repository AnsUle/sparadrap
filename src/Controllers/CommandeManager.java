package Controllers;

import Models.Client;
import Models.Commande;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CommandeManager {
    private List<Commande> commandes;

    // Constructeur
    public CommandeManager() {
        this.commandes = new ArrayList<>();
    }

    // Méthode pour ajouter une commande
    public void ajouterCommande(Commande commande) {
        commandes.add(commande);
        System.out.println("Commande ajoutée avec succès pour le client : " + commande.getClient().getNom() + " " + commande.getClient().getPrenom());
    }

    // Méthode pour supprimer une commande par date et client
    public void supprimerCommande(Date dateCommande, Client client) {
        Optional<Commande> commandeASupprimer = commandes.stream()
                .filter(c -> c.getDateCommande().equals(dateCommande) && c.getClient().equals(client))
                .findFirst();

        if (commandeASupprimer.isPresent()) {
            commandes.remove(commandeASupprimer.get());
            System.out.println("Commande supprimée avec succès pour le client : " + client.getNom() + " " + client.getPrenom());
        } else {
            System.out.println("Commande non trouvée pour le client : " + client.getNom() + " " + client.getPrenom());
        }
    }

    // Méthode pour modifier une commande (identifiée par date et client)
    public void modifierCommande(Date dateCommande, Client client, Commande nouvelleCommande) {
        Optional<Commande> commandeAModifier = commandes.stream()
                .filter(c -> c.getDateCommande().equals(dateCommande) && c.getClient().equals(client))
                .findFirst();

        if (commandeAModifier.isPresent()) {
            commandes.set(commandes.indexOf(commandeAModifier.get()), nouvelleCommande);
            System.out.println("Commande modifiée avec succès pour le client : " + client.getNom() + " " + client.getPrenom());
        } else {
            System.out.println("Commande non trouvée pour le client : " + client.getNom() + " " + client.getPrenom());
        }
    }
    // Nouvelle méthode pour récupérer toutes les commandes
    public List<Commande> getCommandes() {
        return commandes;
    }


    // Méthode pour afficher toutes les commandes
   /* public void afficherToutesLesCommandes() {
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande n'est enregistrée.");
        } else {
            System.out.println("Liste des commandes :");
            for (Commande commande : commandes) {
                System.out.println("Client : " + commande.getClient().getNom() + " " + commande.getClient().getPrenom() +
                        " | Date : " + commande.getDateCommande() +
                        " | Total : " + commande.getTotalPrix() + "€");
            }
        }
    }*/

    // Méthode pour trouver une commande par date et client
    public Commande trouverCommande(Date dateCommande, Client client) {
        return commandes.stream()
                .filter(c -> c.getDateCommande().equals(dateCommande) && c.getClient().equals(client))
                .findFirst()
                .orElse(null);
    }
}
