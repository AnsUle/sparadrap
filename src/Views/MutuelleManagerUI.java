package Views;

import Controllers.MutuelleManager;
import Models.Mutuelle;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MutuelleManagerUI extends JFrame {
    private MutuelleManager mutuelleManager;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton addButton, removeButton, searchButton, refreshButton;

    public MutuelleManagerUI(MutuelleManager mutuelleManager) {
        this.mutuelleManager = mutuelleManager;
        setTitle("Gestion des Mutuelles");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Création de la table pour afficher les mutuelles
        tableModel = new DefaultTableModel(new String[]{"Nom", "Adresse", "Code Postal", "Ville", "Téléphone", "Email", "Taux de Prise en Charge"}, 0);
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

        // Remplir la table avec les mutuelles existantes
        refreshTable();

        // Listener pour le bouton "Ajouter"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjouterMutuelleForm(MutuelleManagerUI.this).setVisible(true);
            }
        });

        // Listener pour le bouton "Supprimer"
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nom = tableModel.getValueAt(selectedRow, 0).toString();
                    Mutuelle mutuelle = mutuelleManager.findMutuelleByName(nom);
                    if (mutuelle != null) {
                        mutuelleManager.removeMutuelle(mutuelle);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une mutuelle à supprimer.");
                }
            }
        });

        // Listener pour le bouton "Rechercher"
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = searchField.getText().trim();
                Mutuelle mutuelle = mutuelleManager.findMutuelleByName(nom);
                if (mutuelle != null) {
                    displayMutuelle(mutuelle);
                } else {
                    JOptionPane.showMessageDialog(null, "Mutuelle non trouvée.");
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

    // Méthode pour ajouter une mutuelle
    public void addMutuelleToManager(Mutuelle mutuelle) {
        mutuelleManager.addMutuelle(mutuelle);
        refreshTable();
    }

    // Rafraîchir le tableau avec les données actuelles
    private void refreshTable() {
        tableModel.setRowCount(0); // Vider la table
        for (Mutuelle mutuelle : mutuelleManager.getMutuelles()) {
            tableModel.addRow(new Object[]{
                    mutuelle.getNom(),
                    mutuelle.getAdresse(),
                    mutuelle.getCodePostal(),
                    mutuelle.getVille(),
                    mutuelle.getTelephone(),
                    mutuelle.getEmail(),
                    mutuelle.getTauxPriseEnCharge()
            });
        }
    }

    // Afficher une mutuelle dans un dialogue
    private void displayMutuelle(Mutuelle mutuelle) {
        JOptionPane.showMessageDialog(this,
                "Nom: " + mutuelle.getNom() +
                        "\nAdresse: " + mutuelle.getAdresse() +
                        "\nCode Postal: " + mutuelle.getCodePostal() +
                        "\nVille: " + mutuelle.getVille() +
                        "\nTéléphone: " + mutuelle.getTelephone() +
                        "\nEmail: " + mutuelle.getEmail() +
                        "\nTaux de Prise en Charge: " + mutuelle.getTauxPriseEnCharge() + "%",
                "Détails de la Mutuelle",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
