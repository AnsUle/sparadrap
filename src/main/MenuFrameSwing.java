package main;

import Controllers.*;
import Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrameSwing extends JFrame {

    private MedecinManager medecinManager;
    private SpecialisteManager specialisteManager;
    private MutuelleManager mutuelleManager;
    private MedicamentManager medicamentManager;
    private ClientManager clientManager;

    public MenuFrameSwing(MedecinManager medecinManager, SpecialisteManager specialisteManager,
                          MutuelleManager mutuelleManager, MedicamentManager medicamentManager,
                          ClientManager clientManager) {
        this.medecinManager = medecinManager;
        this.specialisteManager = specialisteManager;
        this.mutuelleManager = mutuelleManager;
        this.medicamentManager = medicamentManager;
        this.clientManager = clientManager;

        setTitle("Bienvenue Sparadrap + ");
        setSize(600, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Créer un JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 500));

        // Ajouter une image de fond
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/main/resource/Image.jpg"));
        if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Erreur de chargement de l'image");
        }
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, 600, 500);  // Adapter la taille de l'image au panneau
        layeredPane.add(backgroundLabel, Integer.valueOf(1)); // Ajouter l'image dans la couche inférieure

        // Créer un panneau principal transparent
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setOpaque(false);  // Garder le panneau transparent
        panel.setBounds(0, 0, 600, 500);  // Adapter la taille du panneau à la fenêtre

        // Créer des boutons avec des icônes et des couleurs
        JButton btnAjouterCommande = new JButton("Gestion des commandes");
        btnAjouterCommande.setIcon(new ImageIcon("images/Logo.png"));
        btnAjouterCommande.setBackground(Color.BLUE);
        btnAjouterCommande.setForeground(Color.black);
        btnAjouterCommande.setFont(new Font("Arial",Font.ROMAN_BASELINE, 12));

        JButton btnGestionClients = new JButton("Gestion des clients");
        btnGestionClients.setBackground(Color.GREEN);
        btnGestionClients.setForeground(Color.black);
        btnGestionClients.setFont(new Font("Arial",Font.ROMAN_BASELINE, 12));

        JButton btnAfficherMedecin = new JButton("Gestion des medecins");
        btnAfficherMedecin.setBackground(Color.ORANGE);
        btnAfficherMedecin.setForeground(Color.black);
        btnAfficherMedecin.setFont(new Font("Arial",Font.ROMAN_BASELINE, 12));

        JButton btnAfficherSpecialiste = new JButton("Gestion des specialistes");
        btnAfficherSpecialiste.setBackground(Color.CYAN);
        btnAfficherSpecialiste.setForeground(Color.black);
        btnAfficherSpecialiste.setFont(new Font("Arial",Font.ROMAN_BASELINE, 12));

        JButton btnAfficherMutuelle = new JButton("Gestion des Mutuelles");
        btnAfficherMutuelle.setBackground(Color.MAGENTA);
        btnAfficherMutuelle.setForeground(Color.BLACK);
        btnAfficherMutuelle.setFont(new Font("Arial",Font.ROMAN_BASELINE, 12));

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setBackground(Color.RED);
        btnQuitter.setForeground(Color.black);
        btnQuitter.setFont(new Font("Arial",Font.BOLD, 14));

        // Ajouter les boutons au panneau
        panel.add(btnAjouterCommande);
        panel.add(btnGestionClients);
        panel.add(btnAfficherMedecin);
        panel.add(btnAfficherSpecialiste);
        panel.add(btnAfficherMutuelle);
        panel.add(btnQuitter);

        // Ajouter des actions pour chaque bouton
        btnAjouterCommande.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanierUI().setVisible(true);
            }
        });

        btnGestionClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientManagerUI(clientManager, mutuelleManager, medecinManager).setVisible(true);
            }
        });

        btnAfficherMedecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedecinManagerUI(medecinManager).setVisible(true);
            }
        });

        btnAfficherSpecialiste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SpecialisteManagerUI(specialisteManager).setVisible(true);
            }
        });

        btnAfficherMutuelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MutuelleManagerUI(mutuelleManager).setVisible(true);
            }
        });

        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        // Ajouter le panneau transparent au-dessus de l'image
        layeredPane.add(panel, Integer.valueOf(2));  // Mettre le panneau dans une couche supérieure

        // Ajouter le layeredPane au contentPane
        getContentPane().add(layeredPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            MedecinManager medecinManager = new MedecinManager(); // Créer l'instance de MedecinManager
            SpecialisteManager specialisteManager = new SpecialisteManager();
            MutuelleManager mutuelleManager = new MutuelleManager();
            MedicamentManager medicamentManager = new MedicamentManager();
            ClientManager clientManager = new ClientManager(medecinManager, specialisteManager, mutuelleManager);
            MenuFrameSwing menuFrame = new MenuFrameSwing(medecinManager, specialisteManager, mutuelleManager, medicamentManager, clientManager);
            menuFrame.setVisible(true);
        });
    }
}