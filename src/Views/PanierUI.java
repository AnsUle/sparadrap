package Views;

import Controllers.*;
import Models.Client;
import Models.Commande;
import Models.LigneCommande;
import Models.Medicament;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PanierUI extends JFrame {
    private JComboBox<Client> clientComboBox;
    private JTable medicamentTable;
    private JTextArea commandeTextArea;
    private List<Medicament> medicamentsEnStock;
    private Commande commande;
    private CommandeManager commandeManager = new CommandeManager();
    private ClientManager clientManager = new ClientManager(new MedecinManager(),new SpecialisteManager(),new MutuelleManager());
    private MedicamentManager medicamentManager = new MedicamentManager();
    private JCheckBox ordonnanceCheckBox;
    private JTable commandesTable;
    private DefaultTableModel commandesTableModel;
    private JTextField dateRechercheTextField;  // Nouveau champ pour la recherche par date
    private JButton rechercherButton;  // Nouveau bouton de recherche


    public PanierUI() {
        // Initialisation des clients et médicaments
        List<Client> clients = clientManager.getClients();
        medicamentsEnStock = medicamentManager.getMedicaments();

        // Configuration de la fenêtre principale
        setTitle("Gestion des Commandes");
        setSize(1300, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de sélection du client
        JPanel clientPanel = new JPanel();
        clientPanel.add(new JLabel("Sélectionnez un client:"));
        clientComboBox = new JComboBox<>(clients.toArray(new Client[0]));
        clientPanel.add(clientComboBox);

        ordonnanceCheckBox = new JCheckBox("Commande sur ordonnance");
        clientPanel.add(ordonnanceCheckBox);

        add(clientPanel, BorderLayout.NORTH);

        // Ajouter la barre de recherche dans un autre panel
        JPanel recherchePanel = new JPanel();
        recherchePanel.add(new JLabel("Rechercher par date (dd/MM/yyyy):"));
        dateRechercheTextField = new JTextField(10);
        rechercherButton = new JButton("Rechercher");
        recherchePanel.add(dateRechercheTextField);
        recherchePanel.add(rechercherButton);

// Ajouter un nouveau panel pour regrouper les deux parties en haut
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(clientPanel, BorderLayout.NORTH);  // Ajout du panel client
        topPanel.add(recherchePanel, BorderLayout.SOUTH);  // Ajout de la barre de recherche

// Ajouter le topPanel à la fenêtre principale
        add(topPanel, BorderLayout.NORTH);

        // Table des médicaments disponibles
        String[] columnNames = {"Nom", "Prix", "Quantité disponible"};
        Object[][] data = new Object[medicamentsEnStock.size()][3];
        for (int i = 0; i < medicamentsEnStock.size(); i++) {
            Medicament medicament = medicamentsEnStock.get(i);
            data[i][0] = medicament.getNom();
            data[i][1] = medicament.getPrix();
            data[i][2] = medicament.getQuantite();
        }

        medicamentTable = new JTable(data, columnNames);
        add(new JScrollPane(medicamentTable), BorderLayout.CENTER);

        // Panel pour les boutons et champs d'ajout de médicament
        JPanel actionPanel = new JPanel();
        JButton ajouterMedicamentButton = new JButton("Ajouter Médicament");
        JButton supprimerMedicamentButton = new JButton("Supprimer Médicament");
        JButton modifierQuantiteButton = new JButton("Modifier Quantité");
        JButton validerCommandeButton = new JButton("Valider Commande");
        actionPanel.add(ajouterMedicamentButton);
        actionPanel.add(supprimerMedicamentButton);
        actionPanel.add(modifierQuantiteButton);
        actionPanel.add(validerCommandeButton);
        add(actionPanel, BorderLayout.SOUTH);

        // Zone de texte pour afficher la commande
        commandeTextArea = new JTextArea(10, 40);
        commandeTextArea.setEditable(false);
        add(new JScrollPane(commandeTextArea), BorderLayout.EAST);

        // Initialisation d'une commande sans ordonnance par défaut
        commande = new Commande(clients.get(0), false);

        // Table pour afficher les commandes validées
        String[] commandesColumnNames = {"Client", "Date","Medicament", "Total (€)","Ordonnance",};
        commandesTableModel = new DefaultTableModel(commandesColumnNames, 0);
        commandesTable = new JTable(commandesTableModel);
        add(new JScrollPane(commandesTable), BorderLayout.WEST);


        // Gestion des actions
        ajouterMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterMedicamentALaCommande();
            }
        });

        ordonnanceCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ordonnanceCheckBox.isSelected()) {
                    // Ouvre la fenêtre OrdonnanceSwing
                    new OrdonnanceSwing();  // Cela lancera la fenêtre de saisie d'ordonnance
                }
            }
        });

        supprimerMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerMedicamentDeLaCommande();
            }
        });

        modifierQuantiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierQuantiteMedicament();
            }
        });

        validerCommandeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validerCommande();
                mettreAJourQuantitesMedicaments();
            }
        });

        commandesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = commandesTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        afficherDetailsCommande(selectedRow);
                    }
                }
            }
        });

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherCommandeParDate();
            }
        });

        setVisible(true);
    }

    private void rechercherCommandeParDate() {
        String dateRecherche = dateRechercheTextField.getText().trim();
        if (dateRecherche.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une date.");
            return;
        }

        DefaultTableModel newModel = new DefaultTableModel(new String[]{"Client", "Date", "Medicament", "Total (€)", "Ordonnance"}, 0);

        for (int i = 0; i < commandesTableModel.getRowCount(); i++) {
            String dateCommande = commandesTableModel.getValueAt(i, 1).toString();
            if (dateCommande.contains(dateRecherche)) {
                Object[] row = {
                        commandesTableModel.getValueAt(i, 0),
                        commandesTableModel.getValueAt(i, 1),
                        commandesTableModel.getValueAt(i, 2),
                        commandesTableModel.getValueAt(i, 3),
                        commandesTableModel.getValueAt(i, 4)
                };
                newModel.addRow(row);
            }
        }

        commandesTable.setModel(newModel);
    }

    private void validerCommande() {
        if (commande.getLignesCommande().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun médicament ajouté à la commande.");
        } else {
            commandeManager.ajouterCommande(commande);
            JOptionPane.showMessageDialog(this, "Commande validée avec succès !");

            StringBuilder medicamentList = new StringBuilder();
            for (LigneCommande ligneCommande : commande.getLignesCommande()) {
                medicamentList.append(ligneCommande.getMedicament().getNom()).append(", ");
            }
// Supprimer la dernière virgule et espace
            if (medicamentList.length() > 0) {
                medicamentList.setLength(medicamentList.length() - 2);
            }

            Object[] rowData = {
                    commande.getClient().getNom(),
                    commande.getDateCommande(),
                    medicamentList.toString(),
                    String.format("%.2f", commande.calculerTotal()),
                    commande.estSurOrdonnance() ? "Oui" : "Non"
            };
            commandesTableModel.addRow(rowData);


            // Ajouter la commande au tableau des commandes validées
            /*Object[] rowData = {
                    commande.getClient().getNom(),
                    commande.getDateCommande(),
                    String.format("%.2f", commande.calculerTotal()),
                    commande.estSurOrdonnance() ? "Oui" : "Non"
            };
            commandesTableModel.addRow(rowData);*/

            // Réinitialiser la commande après validation
            commande = new Commande((Client) clientComboBox.getSelectedItem(), ordonnanceCheckBox.isSelected());
            commandeTextArea.setText("");
        }
    }

    private void mettreAJourQuantitesMedicaments() {
        for (LigneCommande ligneCommande : commande.getLignesCommande()) {
            Medicament medicament = ligneCommande.getMedicament();
            int quantiteCommandee = ligneCommande.getQuantite();

            // Rechercher le médicament en stock correspondant
            for (Medicament medicamentEnStock : medicamentsEnStock) {
                if (medicamentEnStock.getNom().equals(medicament.getNom())) {
                    // Mettre à jour la quantité disponible
                    int nouvelleQuantite = medicamentEnStock.getQuantite() - quantiteCommandee;
                    medicamentEnStock.setQuantite(nouvelleQuantite);
                    break;
                }
            }
        }
        // Mettre à jour le modèle de la table des médicaments pour refléter les changements
        mettreAJourTableMedicaments();
    }

    private void mettreAJourTableMedicaments() {
        String[] columnNames = {"Nom", "Prix", "Quantité disponible"};
        Object[][] data = new Object[medicamentsEnStock.size()][3];
        for (int i = 0; i < medicamentsEnStock.size(); i++) {
            Medicament medicament = medicamentsEnStock.get(i);
            data[i][0] = medicament.getNom();
            data[i][1] = medicament.getPrix();
            data[i][2] = medicament.getQuantite();
        }
        medicamentTable.setModel(new DefaultTableModel(data, columnNames));
    }


    private void ajouterMedicamentALaCommande() {
        int selectedRow = medicamentTable.getSelectedRow();
        if (selectedRow >= 0) {
            Medicament medicamentSelectionne = medicamentsEnStock.get(selectedRow);
            String quantiteStr = JOptionPane.showInputDialog(this, "Quantité à ajouter:");
            try {
                int quantite = Integer.parseInt(quantiteStr);
                commande.ajouterMedicament(medicamentSelectionne, quantite);
                afficherCommande();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une quantité valide.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un médicament.");
        }
    }

    private void supprimerMedicamentDeLaCommande() {
        int selectedRow = medicamentTable.getSelectedRow();
        if (selectedRow >= 0) {
            Medicament medicamentSelectionne = medicamentsEnStock.get(selectedRow);
            commande.retirerMedicament(medicamentSelectionne);
            afficherCommande();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un médicament à supprimer.");
        }
    }

    private void modifierQuantiteMedicament() {
        int selectedRow = medicamentTable.getSelectedRow();
        if (selectedRow >= 0) {
            Medicament medicamentSelectionne = medicamentsEnStock.get(selectedRow);
            String quantiteStr = JOptionPane.showInputDialog(this, "Nouvelle quantité:");
            try {
                int nouvelleQuantite = Integer.parseInt(quantiteStr);
                commande.modifierQuantite(medicamentSelectionne, nouvelleQuantite);
                afficherCommande();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une quantité valide.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un médicament.");
        }
    }

    private void afficherCommande() {
        commandeTextArea.setText("");
        StringBuilder commandeDetails = new StringBuilder();
        commandeDetails.append("Commande du client : ").append(commande.getClient()).append("\n");
        commandeDetails.append("Date de la commande : ").append(commande.getDateCommande()).append(" (Ordonnance: ").append(commande.estSurOrdonnance()).append(")\n\n");
        double total = 0;
        for (LigneCommande ligneCommande : commande.getLignesCommande()) {
            Medicament medicament = ligneCommande.getMedicament();
            int quantite = ligneCommande.getQuantite();
            double sousTotal = ligneCommande.getSousTotal();
            total += sousTotal;
            commandeDetails.append(quantite).append(" x ").append(medicament.getNom()).append(" = ").append(sousTotal).append(" €\n");
        }
        commandeDetails.append("\nTotal : ").append(total).append(" €");
        commandeTextArea.setText(commandeDetails.toString());
    }

    private void afficherDetailsCommande(int selectedRow) {
        Commande commandeSelectionnee = commandeManager.getCommandes().get(selectedRow);

        StringBuilder commandeDetails = new StringBuilder();
        commandeDetails.append("Détails de la commande\n");
        commandeDetails.append("Client : ").append(commandeSelectionnee.getClient().getNom()).append("\n");
        commandeDetails.append("Date : ").append(commandeSelectionnee.getDateCommande()).append("\n");
        commandeDetails.append("Ordonnance : ").append(commandeSelectionnee.estSurOrdonnance() ? "Oui" : "Non").append("\n\n");

        double total = 0;
        for (LigneCommande ligneCommande : commandeSelectionnee.getLignesCommande()) {
            Medicament medicament = ligneCommande.getMedicament();
            int quantite = ligneCommande.getQuantite();
            double sousTotal = ligneCommande.getSousTotal();
            total += sousTotal;
            commandeDetails.append(quantite).append(" x ").append(medicament.getNom()).append(" = ").append(sousTotal).append(" €\n");
        }
        commandeDetails.append("\nTotal : ").append(total).append(" €");

        commandeTextArea.setText(commandeDetails.toString());
    }

   /* private List<Client> initialiserClients() {
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client("Durand", "Paul", "123 Rue des Clients", "75001", "Paris", "0142003000", "paul.durand@example.com", "1234567890", new Date(), null, null, null);
        Client client2 = new Client("Martin", "Sophie", "456 Avenue des Clients", "69002", "Lyon", "0478009000", "sophie.martin@example.com", "0987654321", new Date(), null, null, null);
        clients.add(client1);
        clients.add(client2);
        return clients;
    }*/

    /*private List<Medicament> initialiserMedicaments() {
        List<Medicament> medicaments = new ArrayList<>();
        Medicament medicament1 = new Medicament("Paracétamol", "Analgesique", 2.5f, new Date(), 100);
        Medicament medicament2 = new Medicament("Ibuprofène", "Anti-inflammatoire", 5.0f, new Date(), 50);
        Medicament medicament3 = new Medicament("Amoxicilline", "Antibiotique", 7.0f, new Date(), 30);

        medicaments.add(medicament1);
        medicaments.add(medicament2);
        medicaments.add(medicament3);

        return medicaments;
    }*/
}
