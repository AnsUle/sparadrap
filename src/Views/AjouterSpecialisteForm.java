package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.Specialiste;
import Controllers.SpecialisteManager;
import Utilitaire.InputValidator;

public class AjouterSpecialisteForm extends JFrame { // Renommé en AjouterSpecialisteForm
    private JTextField nomField, prenomField, adresseField, codePostalField, villeField, telephoneField, emailField, specialiteField;
    private JButton submitButton;
    private SpecialisteManager specialisteManager;

    public AjouterSpecialisteForm(SpecialisteManager specialisteManager) { // Constructeur mis à jour
        this.specialisteManager = specialisteManager; // Initialisation du manager
        setTitle("Ajouter un Spécialiste");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 2));

        // Création des labels et champs de texte
        panel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        panel.add(prenomField);

        panel.add(new JLabel("Adresse:"));
        adresseField = new JTextField();
        panel.add(adresseField);

        panel.add(new JLabel("Code Postal:"));
        codePostalField = new JTextField();
        panel.add(codePostalField);

        panel.add(new JLabel("Ville:"));
        villeField = new JTextField();
        panel.add(villeField);

        panel.add(new JLabel("Téléphone:"));
        telephoneField = new JTextField();
        panel.add(telephoneField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Spécialité:"));
        specialiteField = new JTextField();
        panel.add(specialiteField);

        // Bouton de soumission
        submitButton = new JButton("Enregistrer");
        panel.add(submitButton);

        add(panel);

        // Action Listener pour le bouton de soumission
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Récupération des données des champs
                    String nom = nomField.getText();
                    String prenom = prenomField.getText();
                    String adresse = adresseField.getText();
                    String codePostal = codePostalField.getText();
                    String ville = villeField.getText();
                    String telephone = telephoneField.getText();
                    String email = emailField.getText();
                    String specialite = specialiteField.getText();

                    // Validation des données (si nécessaire) avant de créer l'objet
                    if (InputValidator.isValidEmail(email) && InputValidator.isValidSpecialite(specialite)) {
                        // Création de l'objet Spécialiste
                        Specialiste specialiste = new Specialiste(nom, prenom, adresse, codePostal, ville, telephone, email, specialite);

                        // Ajout du spécialiste au manager
                        specialisteManager.addSpecialiste(specialiste);

                        JOptionPane.showMessageDialog(null, "Spécialiste enregistré avec succès !");
                    } else {
                        JOptionPane.showMessageDialog(null, "Validation échouée. Veuillez vérifier les informations saisies.");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });
    }

}
