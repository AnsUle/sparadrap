package Views;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.Specialiste;
import Controllers.SpecialisteManager;

public class SpecialisteManagerUI extends JFrame {
    private SpecialisteManager specialisteManager;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton addButton, removeButton, searchButton, refreshButton;

    public SpecialisteManagerUI(SpecialisteManager specialisteManager) {
        this.specialisteManager = specialisteManager;
        setTitle("Gestion des Spécialistes");
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
        refreshTable();

        // Listener pour le bouton "Ajouter"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjouterSpecialisteForm(specialisteManager).setVisible(true);
            }
        });

        // Listener pour le bouton "Supprimer"
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nom = tableModel.getValueAt(selectedRow, 0).toString();
                    Specialiste specialiste = specialisteManager.findSpecialisteByName(nom);
                    if (specialiste != null) {
                        specialisteManager.removeSpecialiste(specialiste);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un spécialiste à supprimer.");
                }
            }
        });

        // Listener pour le bouton "Rechercher"
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = searchField.getText();
                Specialiste specialiste = specialisteManager.findSpecialisteByName(nom);
                if (specialiste != null) {
                    displaySpecialiste(specialiste);
                } else {
                    JOptionPane.showMessageDialog(null, "Spécialiste non trouvé.");
                }
            }
        });

        // Listener pour le bouton "Rafraîchir"
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
    }

    // Rafraîchir le tableau avec les données actuelles
    private void refreshTable() {
        tableModel.setRowCount(0); // Vider la table
        for (Specialiste specialiste : specialisteManager.getSpecialistes()) {
            tableModel.addRow(new Object[]{
                    specialiste.getNom(),
                    specialiste.getPrenom(),
                    specialiste.getAdresse(),
                    specialiste.getCodePostal(),
                    specialiste.getVille(),
                    specialiste.getTelephone(),
                    specialiste.getEmail(),
                    specialiste.getSpecialite()
            });
        }
    }

    // Afficher un spécialiste dans un dialogue
    private void displaySpecialiste(Specialiste specialiste) {
        JOptionPane.showMessageDialog(this,
                "Nom: " + specialiste.getNom() +
                        "\nPrénom: " + specialiste.getPrenom() +
                        "\nAdresse: " + specialiste.getAdresse() +
                        "\nCode Postal: " + specialiste.getCodePostal() +
                        "\nVille: " + specialiste.getVille() +
                        "\nTéléphone: " + specialiste.getTelephone() +
                        "\nEmail: " + specialiste.getEmail() +
                        "\nSpécialité: " + specialiste.getSpecialite(),
                "Détails du Spécialiste",
                JOptionPane.INFORMATION_MESSAGE);
    }

}

