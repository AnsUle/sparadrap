package Views;

import Controllers.MedicamentManager;
import Models.Medicament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MedicamentFrame extends JFrame {
    private MedicamentManager medicamentManager;

    // Champs de saisie pour le médicament
    private JTextField tfNom;
    private JTextField tfCategorie;
    private JTextField tfPrix;
    private JTextField tfQuantite;
    private JTextArea taMedicamentList;

    public MedicamentFrame(MedicamentManager medicamentManager) {
        this.medicamentManager = medicamentManager;

        // Configuration de la fenêtre
        setTitle("Gestion des Médicaments");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des composants
        JLabel lblNom = new JLabel("Nom:");
        tfNom = new JTextField(20);

        JLabel lblCategorie = new JLabel("Catégorie:");
        tfCategorie = new JTextField(20);

        JLabel lblPrix = new JLabel("Prix:");
        tfPrix = new JTextField(20);

        JLabel lblQuantite = new JLabel("Quantité:");
        tfQuantite = new JTextField(20);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnAfficher = new JButton("Afficher");

        taMedicamentList = new JTextArea(10, 50);
        taMedicamentList.setEditable(false);

        // Création du panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(lblNom);
        panel.add(tfNom);
        panel.add(lblCategorie);
        panel.add(tfCategorie);
        panel.add(lblPrix);
        panel.add(tfPrix);
        panel.add(lblQuantite);
        panel.add(tfQuantite);
        panel.add(btnAjouter);
        panel.add(btnModifier);
        panel.add(btnSupprimer);
        panel.add(btnAfficher);

        JPanel listPanel = new JPanel();
        listPanel.add(new JScrollPane(taMedicamentList));

        add(panel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);

        // Action du bouton Ajouter
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nom = tfNom.getText();
                    String categorie = tfCategorie.getText();
                    float prix = Float.parseFloat(tfPrix.getText());
                    int quantite = Integer.parseInt(tfQuantite.getText());

                    Medicament medicament = new Medicament(nom, categorie, prix, new Date(), quantite);
                    medicamentManager.ajouterMedicament(medicament);

                    JOptionPane.showMessageDialog(null, "Médicament ajouté!");
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton Modifier
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nom = tfNom.getText();
                    Medicament medicament = medicamentManager.trouverMedicamentParNom(nom);
                    if (medicament != null) {
                        String nouvelleCategorie = tfCategorie.getText();
                        float nouveauPrix = Float.parseFloat(tfPrix.getText());
                        int nouvelleQuantite = Integer.parseInt(tfQuantite.getText());

                        medicamentManager.modifierMedicament(medicament, nom, nouvelleCategorie, nouveauPrix, nouvelleQuantite);

                        JOptionPane.showMessageDialog(null, "Médicament modifié!");
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Médicament non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton Supprimer
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nom = tfNom.getText();
                    Medicament medicament = medicamentManager.trouverMedicamentParNom(nom);
                    if (medicament != null) {
                        medicamentManager.supprimerMedicament(medicament);

                        JOptionPane.showMessageDialog(null, "Médicament supprimé!");
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Médicament non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton Afficher
        btnAfficher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taMedicamentList.setText("");
                for (Medicament medicament : medicamentManager.getMedicaments()) {
                    taMedicamentList.append(medicament.toString() + "\n");
                }
            }
        });
    }

    // Méthode pour vider les champs de saisie
    private void clearFields() {
        tfNom.setText("");
        tfCategorie.setText("");
        tfPrix.setText("");
        tfQuantite.setText("");
    }
}

