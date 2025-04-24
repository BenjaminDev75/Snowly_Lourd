package vue;

import controleur.Controleur;
import controleur.Proprietaire;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelProprietaire extends PanelPrincipal implements ActionListener, KeyListener, MouseListener {

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
        super("Visualisation des Propriétaires");

        // Rendre les champs écoutables
        this.txtNom.addKeyListener(this);
        this.txtPrenom.addKeyListener(this);
        this.txtAdresse.addKeyListener(this);

        // Installation de la JTable
        String entetes[] = {"ID_Utilisateur", "Id Propriétaire", "Nom", "Prénom", "Adresse"};
        this.tableauProprietaires = new Tableau(this.obtenirDonnees(""), entetes);
        this.tableProprietaires = new JTable(this.tableauProprietaires);
        JScrollPane uneScroll = new JScrollPane(this.tableProprietaires);
        uneScroll.setBounds(400, 100, 600, 250);
        this.add(uneScroll);

        // Installation du panel Filtre
        this.panelFiltre.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.panelFiltre.setBounds(430, 60, 550, 30);
        this.panelFiltre.setLayout(new GridLayout(1, 3, 10, 10)); // Espacement entre les composants
        this.panelFiltre.add(createLabel("Filtrer les propriétaires par :"));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(createButton(btFiltrer));
        this.add(this.panelFiltre);
        this.btFiltrer.addActionListener(this);

        // Installation du compteur
        this.lbNbClient.setBounds(630, 380, 400, 20);
        this.lbNbClient.setForeground(Color.WHITE); // Couleur de texte blanche pour contraste
        this.add(this.lbNbClient);
        this.lbNbClient.setText("Nombre de propriétaires : " + this.tableauProprietaires.getRowCount());

        // Rendre la table écoutable sur le clic de la souris
        this.tableProprietaires.addMouseListener(this);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE); // Couleur de texte blanche pour contraste
        return label;
    }

    private JButton createButton(JButton button) {
        button.setBackground(new Color(75, 110, 175)); // Couleur de fond moderne
        button.setForeground(Color.WHITE); // Couleur de texte blanche pour contraste
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Bordures arrondies
        return button;
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Proprietaire> lesProprietaires;
        if (filtre.equals("")) {
            lesProprietaires = Controleur.selectAllProprietaire();
        } else {
            lesProprietaires = Controleur.selectLikeProprietaire(filtre);
        }
        Object matrice[][] = new Object[lesProprietaires.size()][5];
        int i = 0;
        for (Proprietaire unProprietaire : lesProprietaires) {
            matrice[i][0] = unProprietaire.getID_Utilisateur();
            matrice[i][1] = unProprietaire.getIdProprietaire();
            matrice[i][2] = unProprietaire.getNom();
            matrice[i][3] = unProprietaire.getPrenom();
            matrice[i][4] = unProprietaire.getAdresse();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btFiltrer) {
            String filtre = txtFiltre.getText();
            this.tableauProprietaires.setDonnees(this.obtenirDonnees(filtre));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Logique de traitement
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        int numLigne = 0;
        if (e.getClickCount() >= 1) {
            numLigne = tableProprietaires.getSelectedRow();
            txtNom.setText(tableauProprietaires.getValueAt(numLigne, 2).toString());
            txtPrenom.setText(tableauProprietaires.getValueAt(numLigne, 3).toString());
            txtAdresse.setText(tableauProprietaires.getValueAt(numLigne, 4).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
