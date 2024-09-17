package Views;

import Models.Mutuelle;
import Utilitaire.InputValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterMutuelleForm extends JFrame {
    private JTextField nomField, adresseField, codePostalField, villeField, telephoneField, emailField, tauxField;
    private JButton saveButton;
    private MutuelleManagerUI parentUI;

    // Constructeur prenant MutuelleManagerUI comme paramètre
    public AjouterMutuelleForm(MutuelleManagerUI parentUI) {
        this.parentUI = parentUI;  // Référence à l'UI parent

        setTitle("Ajouter une nouvelle Mutuelle");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des champs de saisie
        JPanel panel = new JPanel(new GridLayout(8, 2));

        panel.add(new JLabel("Nom :"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Adresse :"));
        adresseField = new JTextField();
        panel.add(adresseField);

        panel.add(new JLabel("Code Postal :"));
        codePostalField = new JTextField();
        panel.add(codePostalField);

        panel.add(new JLabel("Ville :"));
        villeField = new JTextField();
        panel.add(villeField);

        panel.add(new JLabel("Téléphone :"));
        telephoneField = new JTextField();
        panel.add(telephoneField);

        panel.add(new JLabel("Email :"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Taux de Prise en Charge :"));
        tauxField = new JTextField();
        panel.add(tauxField);

        // Bouton "Enregistrer"
        saveButton = new JButton("Enregistrer");
        panel.add(saveButton);

        add(panel, BorderLayout.CENTER);

        // Action du bouton "Enregistrer"
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validation des données et création d'une nouvelle mutuelle
                    String nom = nomField.getText().trim();
                    String adresse = adresseField.getText().trim();
                    String codePostal = codePostalField.getText().trim();
                    String ville = villeField.getText().trim();
                    String telephone = telephoneField.getText().trim();
                    String email = emailField.getText().trim();
                    float taux = Float.parseFloat(tauxField.getText().trim());

                    if (InputValidator.isValidName(nom) &&
                            InputValidator.isValidAdresse(adresse) &&
                            InputValidator.isValidCodePostal(codePostal) &&
                            InputValidator.isValidVille(ville) &&
                            InputValidator.isValidTelephone(telephone) &&
                            InputValidator.isValidEmail(email) &&
                            InputValidator.isValidTauxPriseEnCharge(taux)) {

                        Mutuelle nouvelleMutuelle = new Mutuelle(nom, adresse, codePostal, ville, telephone, email, taux);

                        // Appel à la méthode addMutuelleToManager via l'instance parent
                        parentUI.addMutuelleToManager(nouvelleMutuelle);
                        JOptionPane.showMessageDialog(null, "Mutuelle ajoutée avec succès !");
                        dispose();  // Fermer la fenêtre après l'ajout
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez entrer des informations valides !");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un taux de prise en charge valide !");
                }
            }
        });
    }
}
