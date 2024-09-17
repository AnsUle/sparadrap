package Models;

public class LigneCommande {
    private Medicament medicament;
    private int quantite;

    public LigneCommande(Medicament medicament, int quantite) {
        this.medicament = medicament;
        this.quantite = quantite;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getSousTotal() {
        return medicament.getPrix() * quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
