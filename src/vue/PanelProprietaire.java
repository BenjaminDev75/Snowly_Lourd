package vue;


import controleur.Controleur;
import controleur.Proprietaire;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelProprietaire extends PanelPrincipal implements ActionListener, KeyListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAdresse = new JTextField();



    private JTable tableProprietaires;
    private Tableau tableauProprietaires;

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");


    private JLabel lbNbClient = new JLabel();


    public PanelProprietaire() {
        super("Visualisation des Proprietaire");


        this.txtNom.addKeyListener(this);
        this.txtPrenom.addKeyListener(this);
        this.txtAdresse.addKeyListener(this);


        // installation de la JTable
        String entetes[] = {"ID_Utilisateur","Id Proprietaire","Nom","Prenom","Adresse"};

        this.tableauProprietaires = new Tableau(this.obtenirDonnees(""),entetes);
        this.tableProprietaires = new JTable(this.tableauProprietaires);
        JScrollPane uneScroll = new JScrollPane(this.tableProprietaires);
        uneScroll.setBounds(400,100,600,250);
        this.add(uneScroll);

        // installation du panel Filtre
        this.panelFiltre.setBackground(Color.cyan);
        this.panelFiltre.setBounds(430,60,550,30);
        this.panelFiltre.setLayout(new GridLayout(1,3));
        this.panelFiltre.add(new JLabel("Filtrer les proprietaires par :"));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(this.btFiltrer);
        this.add(this.panelFiltre);
        this.btFiltrer.addActionListener(this);


        // installation du compteur
        this.lbNbClient.setBounds(630,380,400,20);
        this.add(this.lbNbClient);
        this.lbNbClient.setText("Nombre de proprietaire : " + this.tableauProprietaires.getRowCount());


        // rendre la table ecoutable sur le clic de la souris
        this.tableProprietaires.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                if (e.getClickCount() >=1) {
                    numLigne = tableProprietaires.getSelectedRow();
                    txtNom.setText(tableauProprietaires.getValueAt(numLigne,2).toString());
                    txtPrenom.setText(tableauProprietaires.getValueAt(numLigne,3).toString());
                    txtAdresse.setText(tableauProprietaires.getValueAt(numLigne,4).toString());

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

    }

    public Object [][] obtenirDonnees(String filtre){
        // convertir une ArrayList d'objet de clients en matrice d'element
        ArrayList<Proprietaire> lesProprietaires;
        if(filtre.equals("")){
            lesProprietaires = Controleur.selectAllProprietaire();
        }
        else {
            lesProprietaires = Controleur.selectLikeProprietaire(filtre);
        }
        Object matrice[][] = new Object[lesProprietaires.size()][5];
        int i =0;
        for(Proprietaire unProprietaire : lesProprietaires){
            matrice[i][0] = unProprietaire.getID_Utilisateur();
            matrice[i][1] = unProprietaire.getIdProprietaire();
            matrice[i][2] = unProprietaire.getNom();
            matrice[i][3] = unProprietaire.getPrenom();
            matrice[i][4] = unProprietaire.getAdresse();
            i++;
        }
        return matrice;
    }

    // vide les champs

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
           // traitement();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
