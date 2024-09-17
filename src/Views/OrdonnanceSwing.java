package Views;

import Controllers.*;
import Models.Client;
import Models.Medecin;
import Models.Medicament;
import Models.Ordonnance;
import Models.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrdonnanceSwing extends JFrame {
    private MedecinManager medecinManager;
    private ClientManager clientManager;
    private MedicamentManager medicamentManager;
    private SpecialisteManager specialisteManager;
    private MutuelleManager mutuelleManager;
    private OrdonnanceManager ordonnanceManager;

    private JComboBox<Medecin> medecinCombo;
    private JComboBox<Client> patientCombo;
    private JComboBox<Medicament> medicamentCombo;
    private JComboBox<Specialiste> specialisteCombo;

    private JTextArea ordonnanceInfo;
    private JButton createOrdonnanceButton;
    private JButton addMedicamentButton;
    private JTextField dateField; // Utiliser un JTextField pour la date
    private JTable medicamentTable; // Tableau pour afficher les médicaments ajoutés
    private DefaultTableModel medicamentTableModel;

    private List<Medicament> medicamentsSelectionnes;

    public OrdonnanceSwing() {
        medecinManager = new MedecinManager();
        mutuelleManager = new MutuelleManager();
        clientManager = new ClientManager(medecinManager, specialisteManager, mutuelleManager);
        medicamentManager = new MedicamentManager();
        specialisteManager = new SpecialisteManager();
        ordonnanceManager = new OrdonnanceManager();

        setTitle("Création d'Ordonnance");
        setSize(800, 600); // Ajustez la taille en fonction des besoins
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialiser la liste de médicaments sélectionnés
        medicamentsSelectionnes = new ArrayList<>();

        // Créer les composants
        medecinCombo = new JComboBox<>();
        patientCombo = new JComboBox<>();
        medicamentCombo = new JComboBox<>();
        specialisteCombo = new JComboBox<>();
        ordonnanceInfo = new JTextArea(10, 50);
        createOrdonnanceButton = new JButton("Créer Ordonnance");
        addMedicamentButton = new JButton("Ajouter Médicament");
        dateField = new JTextField("JJ/MM/AAAA");

        // Initialiser le modèle du tableau pour les médicaments
        medicamentTableModel = new DefaultTableModel(new String[]{"Médicament", "Quantité"}, 0);
        medicamentTable = new JTable(medicamentTableModel);

        // Ajouter les composants à la fenêtre
        JPanel topPanel = new JPanel(new GridLayout(6, 2)); // Ajuster le nombre de lignes en fonction des composants
        topPanel.add(new JLabel("Médecin:"));
        topPanel.add(medecinCombo);
        topPanel.add(new JLabel("Patient:"));
        topPanel.add(patientCombo);
        topPanel.add(new JLabel("Médicament:"));
        topPanel.add(medicamentCombo);
        topPanel.add(new JLabel("Spécialiste:"));
        topPanel.add(specialisteCombo);
        topPanel.add(new JLabel("Date (JJ/MM/AAAA):"));
        topPanel.add(dateField);
        topPanel.add(addMedicamentButton);
        add(topPanel, BorderLayout.NORTH);

        // Ajouter le tableau de médicaments
        add(new JScrollPane(medicamentTable), BorderLayout.CENTER);

        add(new JScrollPane(ordonnanceInfo), BorderLayout.EAST);
        add(createOrdonnanceButton, BorderLayout.SOUTH);

        // Remplir les JComboBox
        remplirMedecinsComboBox();
        remplirPatientsComboBox();
        remplirMedicamentsComboBox();
        remplirSpecialistesComboBox();

        // Ajouter un écouteur d'action pour le bouton
        createOrdonnanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrdonnance();
            }
        });

        addMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMedicament();
            }
        });

        setVisible(true);
    }

    private void remplirMedecinsComboBox() {
        List<Medecin> medecins = medecinManager.getTousLesMedecins();
        DefaultComboBoxModel<Medecin> model = new DefaultComboBoxModel<>(medecins.toArray(new Medecin[0]));
        medecinCombo.setModel(model);
    }

    private void remplirPatientsComboBox() {
        List<Client> clients = clientManager.getClients();
        DefaultComboBoxModel<Client> model = new DefaultComboBoxModel<>(clients.toArray(new Client[0]));
        patientCombo.setModel(model);
    }

    private void remplirMedicamentsComboBox() {
        List<Medicament> medicaments = medicamentManager.getMedicaments();
        DefaultComboBoxModel<Medicament> model = new DefaultComboBoxModel<>(medicaments.toArray(new Medicament[0]));
        medicamentCombo.setModel(model);
    }

    private void remplirSpecialistesComboBox() {
        List<Specialiste> specialistes = specialisteManager.getSpecialistes();
        DefaultComboBoxModel<Specialiste> model = new DefaultComboBoxModel<>(specialistes.toArray(new Specialiste[0]));
        specialisteCombo.setModel(model);
    }

    private void addMedicament() {
        Medicament selectedMedicament = (Medicament) medicamentCombo.getSelectedItem();
        if (selectedMedicament != null) {
            medicamentsSelectionnes.add(selectedMedicament);
            medicamentTableModel.addRow(new Object[]{selectedMedicament.getNom(), "1"}); // Par défaut, ajouter 1 pour la quantité
        }
    }

    private void createOrdonnance() {
        Medecin selectedMedecin = (Medecin) medecinCombo.getSelectedItem();
        Client selectedPatient = (Client) patientCombo.getSelectedItem();
        Specialiste selectedSpecialiste = (Specialiste) specialisteCombo.getSelectedItem();
        String dateString = dateField.getText();

        if (selectedMedecin != null && selectedPatient != null && !medicamentsSelectionnes.isEmpty() && !dateString.isEmpty()) {
            try {
                java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
                format.setLenient(false);
                java.util.Date date = format.parse(dateString);

                // Créer une ordonnance avec les informations
                Ordonnance ordonnance = new Ordonnance(
                        date,
                        selectedMedecin,
                        selectedPatient,
                        medicamentsSelectionnes, // Liste contenant tous les médicaments sélectionnés
                        selectedSpecialiste
                );
                ordonnanceManager.ajouterOrdonnance(ordonnance);

                ordonnanceInfo.setText("Ordonnance créée pour: " + selectedPatient.getNom() +
                        "\nMédecin: " + selectedMedecin.getNom() +
                        "\nSpécialiste: " + (selectedSpecialiste != null ? selectedSpecialiste.getNom() : "N/A") +
                        "\n\nMédicaments:\n" + getMedsString());
            } catch (java.text.ParseException ex) {
                ordonnanceInfo.setText("Format de date invalide. Utilisez JJ/MM/AAAA.");
            }
        } else {
            ordonnanceInfo.setText("Veuillez sélectionner toutes les informations requises et entrer une date valide.");
        }
    }


    private String getMedsString() {
        StringBuilder sb = new StringBuilder();
        for (Medicament medicament : medicamentsSelectionnes) {
            sb.append(medicament.getNom()).append("\n");
        }
        return sb.toString();
    }

}
