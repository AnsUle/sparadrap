package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Commande {
    private LocalDate dateCommande;
    private Client client;
    private List<LigneCommande> lignesCommande;
    private boolean estSurOrdonnance;

    // Constructeur
    public Commande(Client client, boolean estSurOrdonnance) {
        this.dateCommande = LocalDate.now();
        this.client = client;
        this.lignesCommande = new ArrayList<>();
        this.estSurOrdonnance = estSurOrdonnance;
    }
//Getter
    public List<LigneCommande> getLignesCommande() {
        return lignesCommande;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public boolean estSurOrdonnance() {
        return estSurOrdonnance;
    }

    public void retirerMedicament(Medicament medicament) {
        lignesCommande.removeIf(ligne -> ligne.getMedicament().equals(medicament));
    }

    public double getTotalPrix() {
        double total = 0;
        for (LigneCommande ligneCommande : lignesCommande) {
            total += ligneCommande.getSousTotal();
        }
        return total;
    }

//modifier la quantité
    public void modifierQuantite(Medicament medicament, int nouvelleQuantite) {
        for (LigneCommande ligneCommande : lignesCommande) {
            if (ligneCommande.getMedicament().equals(medicament)) {
                int quantiteActuelle = ligneCommande.getQuantite();
                int quantiteDisponible = medicament.getQuantite() + quantiteActuelle;
                if (nouvelleQuantite <= quantiteDisponible) {
                    medicament.setQuantite(quantiteDisponible - nouvelleQuantite);
                    ligneCommande.setQuantite(nouvelleQuantite);
                } else {
                    throw new IllegalArgumentException("Quantité demandée non disponible.");
                }
                break;
            }
        }
    }
//Ajouter un medicament
    public void ajouterMedicament(Medicament medicament, int quantite) {
        if (quantite <= medicament.getQuantite()) {
            medicament.setQuantite(medicament.getQuantite() - quantite);
            LigneCommande ligneCommande = new LigneCommande(medicament, quantite);
            lignesCommande.add(ligneCommande);
        } else {
            throw new IllegalArgumentException("Quantité demandée non disponible.");
        }
    }
//Afficher les medicaments
    public String afficherCommande() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commande du client : ").append(client).append("\n");
        sb.append("Date de la commande : ").append(dateCommande).append(" (Ordonnance: ").append(estSurOrdonnance).append(")\n");
        double total = 0;
        for (LigneCommande ligneCommande : lignesCommande) {
            Medicament medicament = ligneCommande.getMedicament();
            int quantite = ligneCommande.getQuantite();
            double sousTotal = ligneCommande.getSousTotal();
            total += sousTotal;
            sb.append(quantite).append(" x ").append(medicament.getNom()).append(" = ").append(sousTotal).append(" €\n");
        }
        sb.append("Total : ").append(total).append(" €\n");
        return sb.toString();
    }
    // Méthode pour calculer le total de la commande
    public double calculerTotal() {
        double total = 0;
        for (LigneCommande ligneCommande : lignesCommande) {
            total += ligneCommande.getSousTotal();
        }
        return total;
    }

}
