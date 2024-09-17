package Views;

import Controllers.ClientManager;
import Controllers.MedecinManager;
import Controllers.MutuelleManager;
import Models.Client;
import Models.Medecin;
import Models.Mutuelle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientManagerUI extends JFrame {
    private ClientManager clientManager;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private MedecinManager medecinManager;
    private MutuelleManager mutuelleManager;

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField adresseField;
    private JTextField codePostalField;
    private JTextField villeField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField numSecuriteSocialField;
    private JTextField searchField;

    private JComboBox<Medecin> medecinComboBox;
    private JComboBox<Mutuelle> mutuelleComboBox;

    public ClientManagerUI(ClientManager clientManager, MutuelleManager mutuelleManager, MedecinManager medecinManager) {
        this.clientManager = clientManager;
        this.medecinManager = medecinManager;
        this.mutuelleManager = mutuelleManager;
        setTitle("Gestion des Clients");
        setSize(1500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Layout principal
        JPanel panel = new JPanel(new BorderLayout());

        // Barre de recherche
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Rechercher par nom:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        // Table pour afficher les clients
        tableModel = new DefaultTableModel(new Object[]{"Nom", "Prénom", "Adresse", "Ville", "Téléphone", "Email", "Numéro SS", "Médecin", "Mutuelle"}, 0);
        clientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientTable);

        // Formulaire d'ajout de client
        JPanel formPanel = new JPanel(new GridLayout(10, 2));

        formPanel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        formPanel.add(nomField);

        formPanel.add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        formPanel.add(prenomField);

        formPanel.add(new JLabel("Adresse:"));
        adresseField = new JTextField();
        formPanel.add(adresseField);

        formPanel.add(new JLabel("Code Postal:"));
        codePostalField = new JTextField();
        formPanel.add(codePostalField);

        formPanel.add(new JLabel("Ville:"));
        villeField = new JTextField();
        formPanel.add(villeField);

        formPanel.add(new JLabel("Téléphone:"));
        telephoneField = new JTextField();
        formPanel.add(telephoneField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Numéro de Sécurité Sociale:"));
        numSecuriteSocialField = new JTextField();
        formPanel.add(numSecuriteSocialField);

        // Combobox pour les médecins
        formPanel.add(new JLabel("Médecin:"));
        medecinComboBox = new JComboBox<>();
        remplirMedecinComboBox();
        formPanel.add(medecinComboBox);

        // Combobox pour les mutuelles
        formPanel.add(new JLabel("Mutuelle:"));
        mutuelleComboBox = new JComboBox<>();
        remplirMutuelleComboBox();
        formPanel.add(mutuelleComboBox);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Ajouter Client");
        JButton deleteButton = new JButton("Supprimer Client");
        JButton searchButton = new JButton("Rechercher");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);

        // Action pour ajouter un client
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterClient();
            }
        });

        // Action pour supprimer un client
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerClient();
            }
        });

        // Action pour le bouton "Rechercher"
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().trim();
                rechercherClient(searchTerm);
            }
        });

        // Ajout des composants au panel principal
        panel.add(searchPanel, BorderLayout.NORTH); // Ajout de la barre de recherche en haut
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(formPanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panel au JFrame
        add(panel);

        // Remplir la table avec les clients existants
        afficherClients();
    }

    // Méthode pour remplir la combobox des médecins
    private void remplirMedecinComboBox() {
        List<Medecin> medecins = medecinManager.getTousLesMedecins();
        for (Medecin medecin : medecins) {
            medecinComboBox.addItem(medecin);
        }
    }

    // Méthode pour remplir la combobox des mutuelles
    private void remplirMutuelleComboBox() {
        List<Mutuelle> mutuelles = mutuelleManager.getMutuelles();
        for (Mutuelle mutuelle : mutuelles) {
            mutuelleComboBox.addItem(mutuelle);
        }
    }

    // Méthode pour ajouter un client
    private void ajouterClient() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String adresse = adresseField.getText();
        String codePostal = codePostalField.getText();
        String ville = villeField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        String numSecuriteSocial = numSecuriteSocialField.getText();

        // Récupérer le médecin et la mutuelle sélectionnés
        Medecin medecin = (Medecin) medecinComboBox.getSelectedItem();
        Mutuelle mutuelle = (Mutuelle) mutuelleComboBox.getSelectedItem();

        Client client = new Client(nom, prenom, adresse, codePostal, ville, telephone, email, numSecuriteSocial, new java.util.Date(), mutuelle, medecin, null);
        clientManager.ajouterClient(client);

        // Mettre à jour la table
        afficherClients();
    }

    // Méthode pour supprimer un client
    private void supprimerClient() {
        int selectedRow = clientTable.getSelectedRow();
        if (selectedRow >= 0) {
            String numSecuriteSocial = (String) clientTable.getValueAt(selectedRow, 6);
            Client client = clientManager.trouverClientParNumSecuriteSocial(numSecuriteSocial);
            if (client != null) {
                clientManager.supprimerClient(client);
                afficherClients();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client à supprimer.");
        }
    }

    // Méthode pour afficher les clients dans la table
    private void afficherClients() {
        tableModel.setRowCount(0); // Vider la table

        List<Client> clients = clientManager.getClients();
        for (Client client : clients) {
            tableModel.addRow(new Object[]{
                    client.getNom(),
                    client.getPrenom(),
                    client.getAdresse(),
                    client.getVille(),
                    client.getTelephone(),
                    client.getEmail(),
                    client.getNumSecuriteSocial(),
                    client.getMedecinTraitant() != null ? client.getMedecinTraitant().getNom() : "Aucun",
                    client.getMutuelle() != null ? client.getMutuelle().getNom() : "Aucune"
            });
        }
    }

    // Méthode pour rechercher et filtrer les clients par nom
    private void rechercherClient(String searchTerm) {
        tableModel.setRowCount(0); // Vider la table

        List<Client> clients = clientManager.getClients();
        for (Client client : clients) {
            if (client.getNom().toLowerCase().contains(searchTerm.toLowerCase())) {
                tableModel.addRow(new Object[]{
                        client.getNom(),
                        client.getPrenom(),
                        client.getAdresse(),
                        client.getVille(),
                        client.getTelephone(),
                        client.getEmail(),
                        client.getNumSecuriteSocial(),
                        client.getMedecinTraitant() != null ? client.getMedecinTraitant().getNom() : "Aucun",
                        client.getMutuelle() != null ? client.getMutuelle().getNom() : "Aucune"
                });
            }
        }
    }
}
