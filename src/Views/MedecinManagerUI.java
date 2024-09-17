package Views;
import Controllers.MedecinManager;
import Models.Medecin;
import Models.Specialiste;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedecinManagerUI extends JFrame {
    private MedecinManager medecinManager;
    private JTable tableMedecins;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField searchField;
    private JButton addButton, removeButton, searchButton, refreshButton;

    public MedecinManagerUI(MedecinManager medecinManager) {
        this.medecinManager = medecinManager;
        setTitle("Gestion des Médecins");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Création de la table pour afficher les spécialistes
        tableModel = new DefaultTableModel(new String[]{"Nom", "Prénom", "Adresse", "Code Postal", "Ville", "Téléphone", "Email", "Spécialité"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel pour les boutons d'action
        JPanel actionPanel = new JPanel();
        addButton = new JButton("Ajouter");
        removeButton = new JButton("Supprimer");
        searchField = new JTextField(20);
        searchButton = new JButton("Rechercher");
        refreshButton = new JButton("Rafraîchir");

        actionPanel.add(addButton);
        actionPanel.add(removeButton);
        actionPanel.add(searchField);
        actionPanel.add(searchButton);
        actionPanel.add(refreshButton);

        panel.add(actionPanel, BorderLayout.SOUTH);
        add(panel);

        // Remplir la table avec les spécialistes existants
        afficherMedecins();

        /*// Création des composants
        JButton ajouterButton = new JButton("Ajouter Médecin");
        JButton afficherButton = new JButton("Afficher Médecins");
        JButton supprimerButton = new JButton("Supprimer Médecin");
        tableModel = new DefaultTableModel(new String[]{"Nom", "Prénom", "Adresse", "Code Postal", "Ville", "Téléphone", "Email", "Numéro d'Agrément"}, 0);
        tableMedecins = new JTable(tableModel);
       scrollPane = new JScrollPane(tableMedecins);

        // Disposition
        JPanel panelButtons = new JPanel();
        panelButtons.add(ajouterButton);
        panelButtons.add(afficherButton);
        panelButtons.add(supprimerButton);

        setLayout(new BorderLayout());
        add(panelButtons, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);*/

        // Action des boutons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjouterMedecinForm(medecinManager).setVisible(true);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherMedecins();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerMedecin();
            }
        });

        // Listener pour le bouton "Rechercher"
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = searchField.getText();
                Medecin medecin = medecinManager.findMedecinByName(nom);
                if (medecin != null) {
                    displayMedecin(medecin);
                } else {
                    JOptionPane.showMessageDialog(null, "Spécialiste non trouvé.");
                }
            }
        });
    }


    private void afficherMedecins() {
        tableModel.setRowCount(0); // Réinitialiser le modèle de table

        for (Medecin medecin : medecinManager.getTousLesMedecins()) {
            tableModel.addRow(new Object[]{
                    medecin.getNom(),
                    medecin.getPrenom(),
                    medecin.getAdresse(),
                    medecin.getCodePostal(),
                    medecin.getVille(),
                    medecin.getTelephone(),
                    medecin.getEmail(),
                    medecin.getNumAgrement()
            });
        }
    }

    private void supprimerMedecin() {
        int selectedRow = tableMedecins.getSelectedRow();
        if (selectedRow >= 0) {
            String numAgrement = (String) tableModel.getValueAt(selectedRow, 7);
            Medecin medecin = medecinManager.trouverMedecinParNumAgrement(numAgrement);
            if (medecin != null) {
                medecinManager.supprimerMedecin(medecin);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Médecin supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Médecin non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un médecin à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
        }

    }
// Afficher un spécialiste dans un dialogue
private void displayMedecin(Medecin medecin) {
    JOptionPane.showMessageDialog(this,
            "Nom: " + medecin.getNom() +
                    "\nPrénom: " + medecin.getPrenom() +
                    "\nAdresse: " + medecin.getAdresse() +
                    "\nCode Postal: " + medecin.getCodePostal() +
                    "\nVille: " + medecin.getVille() +
                    "\nTéléphone: " + medecin.getTelephone() +
                    "\nEmail: " + medecin.getEmail(),
            "Détails du medecin",
            JOptionPane.INFORMATION_MESSAGE);
}
}

