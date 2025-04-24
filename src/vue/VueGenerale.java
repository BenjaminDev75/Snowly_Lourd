package vue;

import controleur.Orange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueGenerale extends JFrame implements ActionListener {

    private JPanel panelMenu = new JPanel();

    private JButton btProfil = new JButton("Profil");
    private JButton btClient = new JButton("Client");
    private JButton btAppartement = new JButton("Appartement");
    private JButton btReservation = new JButton("Réservation");
    private JButton btProprietaire = new JButton("Propriétaire");
    private JButton btStat = new JButton("Stats");
    private JButton btQuitter = new JButton("Quitter");

    // Instances des panels
    private static PanelProfil unPanelProfil = new PanelProfil();
    private static PanelClient unPanelClient = new PanelClient();
    private static PanelProprietaire unPanelTelephone = new PanelProprietaire();
    private static PanelAppartement unPanelIntervention = new PanelAppartement();
    private static PanelReservation unPanelTechnicien = new PanelReservation();
    private static PanelStats unPanelStats = new PanelStats();

    public VueGenerale() {
        this.setTitle("Gestion Neige & Soleil");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(60, 63, 65));
        this.setLayout(null);
        this.setBounds(50, 50, 1500, 900);

        this.panelMenu.setBackground(new Color(60, 63, 65));
        this.panelMenu.setBounds(293, 10, 900, 40);
        this.panelMenu.setLayout(new GridLayout(1, 6));
        this.panelMenu.add(createButton(btProfil));
        this.panelMenu.add(createButton(btClient));
        this.panelMenu.add(createButton(btAppartement));
        this.panelMenu.add(createButton(btReservation));
        this.panelMenu.add(createButton(btProprietaire));
        this.panelMenu.add(createButton(btQuitter));
        this.add(this.panelMenu);

        // Rendre les boutons écoutables
        this.btProfil.addActionListener(this);
        this.btClient.addActionListener(this);
        this.btReservation.addActionListener(this);
        this.btAppartement.addActionListener(this);
        this.btProprietaire.addActionListener(this);
        this.btStat.addActionListener(this);
        this.btQuitter.addActionListener(this);

        // Ajout des panels dans la vue générale
        this.add(unPanelProfil);
        this.add(unPanelClient);
        this.add(unPanelIntervention);
        this.add(unPanelTechnicien);
        this.add(unPanelTelephone);
        this.add(unPanelStats);

        this.setVisible(true);
    }

    private JButton createButton(JButton button) {
        button.setBackground(new Color(75, 110, 175));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return button;
    }

    private void afficherPanel(int choix) {
        unPanelProfil.setVisible(false);
        unPanelClient.setVisible(false);
        unPanelIntervention.setVisible(false);
        unPanelTechnicien.setVisible(false);
        unPanelTelephone.setVisible(false);
        unPanelStats.setVisible(false);

        switch (choix) {
            case 1:
                unPanelProfil.setVisible(true);
                break;
            case 2:
                unPanelClient.setVisible(true);
                break;
            case 3:
                unPanelTechnicien.setVisible(true);
                break;
            case 4:
                unPanelIntervention.setVisible(true);
                break;
            case 5:
                unPanelTelephone.setVisible(true);
                break;
            case 6:
                unPanelStats.setVisible(true);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.btQuitter == e.getSource()) {
            // Rendre invisible la fenêtre VueConnexion
            Orange.rendreVisibleVueConnexion(true);
            // Détruire la vue générale
            Orange.creerVueGenerale(false);
        }

        String choix = e.getActionCommand();
        switch (choix) {
            case "Profil":
                this.afficherPanel(1);
                break;
            case "Client":
                this.afficherPanel(2);
                break;
            case "Appartement":
                this.afficherPanel(4);
                break;
            case "Réservation":
                this.afficherPanel(3);
                break;
            case "Propriétaire":
                this.afficherPanel(5);
                break;
            case "Stats":
                this.afficherPanel(6);
                break;
        }
    }
}
