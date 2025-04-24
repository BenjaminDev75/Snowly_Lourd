package vue;

import controleur.Controleur;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;


public class PanelStats extends PanelPrincipal {

    private static JPanel panelCount = new JPanel();
    private static JLabel lbClients = new JLabel("Nombre de clients : " + Controleur.count("client"));
    private static JLabel lbTechnicien = new JLabel("Nombre de proprietaire : " + Controleur.count("proprietaire"));
    private static JLabel lbTelephone = new JLabel("Nombre de appartement : " + Controleur.count("appartement"));
    private static JLabel lbIntervention = new JLabel("Nombre de reservation : " + Controleur.count("reservation"));

    private JTable tableStats;
    private Tableau tableauStats;

    public PanelStats() {
        super("Gestion des Statistiques");


        panelCount.setBackground(Color.gray);
        panelCount.setBounds(500,200,400,250);
        panelCount.setLayout(new GridLayout(2,2));

        panelCount.add(lbClients);
        panelCount.add(lbTechnicien);
        panelCount.add(lbTelephone);
        panelCount.add(lbIntervention);

        this.add(panelCount);

    }

    public static void actualiser(){
        lbClients.setText("Nombre de clients : " + Controleur.count("client"));
        lbTechnicien.setText("Nombre de proprietaire : " + Controleur.count("proprietaire"));
        lbTelephone.setText("Nombre de appartement : " + Controleur.count("appartement"));
        lbIntervention.setText("Nombre de reservation : " + Controleur.count("reservation"));

    }


}
