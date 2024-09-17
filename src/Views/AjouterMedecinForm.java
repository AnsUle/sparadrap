package Views;

import Models.Medecin;
import Utilitaire.InputValidator;
import Controllers.MedecinManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterMedecinForm extends JFrame {
    private static JTextField nomField;
    private static JTextField prenomField;
    private static JTextField adresseField;
    private static JTextField codePostalField;
    private static JTextField villeField;
    private static JTextField telephoneField;
    private static JTextField emailField;
    private static JTextField numAgrementField;
    private JButton ajouterButton;
    private JButton annulerButton;

    private static MedecinManager medecinManager; // Référence à MedecinManager

    public AjouterMedecinForm(MedecinManager medecinManager) {
        this.medecinManager = medecinManager; // Initialisation de la référence

        setTitle("Ajouter un Médecin");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Créer les composants
        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        adresseField = new JTextField(20);
        codePostalField = new JTextField(10);
        villeField = new JTextField(20);
        telephoneField = new JTextField(15);
        emailField = new JTextField(30);
        numAgrementField = new JTextField(20);

        ajouterButton = new JButton("Ajouter");
        annulerButton = new JButton("Annuler");

        // Disposition
        setLayout(new GridLayout(10, 2));

        add(new JLabel("Nom:"));
        add(nomField);
        add(new JLabel("Prénom:"));
        add(prenomField);
        add(new JLabel("Adresse:"));
        add(adresseField);
        add(new JLabel("Code Postal:"));
        add(codePostalField);
        add(new JLabel("Ville:"));
        add(villeField);
        add(new JLabel("Téléphone:"));
        add(telephoneField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Numéro d'Agrément:"));
        add(numAgrementField);
        add(ajouterButton);
        add(annulerButton);

        // Action des boutons
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterMedecin();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre
            }
        });
    }

    void ajouterMedecin() {
        try {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String adresse = adresseField.getText();
            String codePostal = codePostalField.getText();
            String ville = villeField.getText();
            String telephone = telephoneField.getText();
            String email = emailField.getText();
            String numAgrement = numAgrementField.getText();

            // Validation des champs
            if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || codePostal.isEmpty() ||
                    ville.isEmpty() || telephone.isEmpty() || email.isEmpty() || numAgrement.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation du numéro d'agrément
            if (!InputValidator.isValidNumAgrement(numAgrement)) {
                JOptionPane.showMessageDialog(this, "Numéro d'agrément invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Création du médecin
            Medecin medecin = new Medecin(nom, prenom, adresse, codePostal, ville, telephone, email, numAgrement);

            // Ajout du médecin à la liste via MedecinManager
            medecinManager.ajouterMedecin(medecin);
            JOptionPane.showMessageDialog(this, "Médecin ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

            // Réinitialiser les champs
            nomField.setText("");
            prenomField.setText("");
            adresseField.setText("");
            codePostalField.setText("");
            villeField.setText("");
            telephoneField.setText("");
            emailField.setText("");
            numAgrementField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du médecin: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}

