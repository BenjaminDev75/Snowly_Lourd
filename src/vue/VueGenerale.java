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
    private JButton btReservation = new JButton("Reservation");
    private JButton btProprietaire = new JButton("Proprietaire");
    private JButton btStat = new JButton("Stats");
    private JButton btQuitter = new JButton("Quitter");


    //instance des Panel
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
        this.getContentPane().setBackground(Color.darkGray);
        this.setLayout(null);
        this.setBounds(50,50,1500,900);



        this.panelMenu.setBackground(Color.darkGray);
        this.panelMenu.setBounds(293,10,900,40);
       // this.panelMenu.setLayout(new GridLayout(1,7)); avec btnStat
        this.panelMenu.setLayout(new GridLayout(1,6));
        this.panelMenu.add(btProfil);
        this.panelMenu.add(btClient);
        this.panelMenu.add(btAppartement);
        this.panelMenu.add(btReservation);
        this.panelMenu.add(btProprietaire);
       // this.panelMenu.add(btStat);
        this.panelMenu.add(btQuitter);
        this.add(this.panelMenu);


        //rednre les boutons ecoutables
        this.btProfil.addActionListener(this);
        this.btClient.addActionListener(this);
        this.btReservation.addActionListener(this);
        this.btAppartement.addActionListener(this);
        this.btProprietaire.addActionListener(this);
        this.btStat.addActionListener(this);
        this.btQuitter.addActionListener(this);

        // ajout des panel dans la vue générale
        this.add(unPanelProfil);
        this.add(unPanelClient);
        this.add(unPanelIntervention);
        this.add(unPanelTechnicien);
        this.add(unPanelTelephone);
        this.add(unPanelStats);

        this.setVisible(true);

    }

    private void afficherPanel(int choix){
        unPanelProfil.setVisible(false);
        unPanelClient.setVisible(false);
        unPanelIntervention.setVisible(false);
        unPanelTechnicien.setVisible(false);
        unPanelTelephone.setVisible(false);
        unPanelStats.setVisible(false);

        switch(choix){
            case 1: unPanelProfil.setVisible(true); break;
            case 2: unPanelClient.setVisible(true); break;
            case 3: unPanelTechnicien.setVisible(true); break;
            case 4: unPanelIntervention.setVisible(true); break;
            case 5: unPanelTelephone.setVisible(true); break;
            case 6: unPanelStats.setVisible(true); break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(this.btQuitter == e.getSource()) {
            // rendre invisible la fenetre VueConnexion
            Orange.rendreVisibleVueConnexion(true);
            // detruire la vueGenerale
            Orange.creerVueGenerale(false);
        }

        String choix = e.getActionCommand();
        switch(choix){
            case "Profil":
                this.afficherPanel(1); break;
            case "Client":
                this.afficherPanel(2); break;
            case "Appartement":
                this.afficherPanel(4); break;
            case "Reservation":
                this.afficherPanel(3); break;
            case "Proprietaire":
                this.afficherPanel(5); break;
            case "Stats":
                this.afficherPanel(6); break;
        }

    }
}
