package vue;

import controleur.Appartement;
import controleur.Controleur;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelAppartement extends PanelPrincipal implements ActionListener, KeyListener, MouseListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtCP = new JTextField();
    private JTextField txtVille = new JTextField();
    private JTextField txtExposition = new JTextField();
    private JTextField txtSurfaceHabitable = new JTextField();
    private JTextField txtSurfaceBalcon = new JTextField();
    private JTextField txtCapaciteAcceuil = new JTextField();
    private JTextField txtDistancePiste = new JTextField();
    private JTextField txtDescription = new JTextField();
    private JTextField txtTarif = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    private JTable tableAppartements;
    private Tableau tableauAppartements;

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JButton btSupprimer = new JButton("Supprimer");

    private JLabel lbNbClient = new JLabel();

    public PanelAppartement() {
        super("Gestion des Appartements");

        // Placement du panel formulaire
        this.panelForm.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.panelForm.setBounds(30, 100, 300, 200);
        this.panelForm.setLayout(new GridLayout(6, 2, 10, 10)); // Espacement entre les composants
        this.panelForm.add(createLabel("Nom Immeuble :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(createLabel("Adresse :"));
        this.panelForm.add(this.txtAdresse);
        this.panelForm.add(createLabel("Ville :"));
        this.panelForm.add(this.txtVille);
        this.panelForm.add(createLabel("CP :"));
        this.panelForm.add(this.txtCP);
        this.panelForm.add(createButton(btAnnuler));
        this.panelForm.add(createButton(btValider));
        this.add(this.panelForm);

        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        this.txtNom.addKeyListener(this);
        this.txtAdresse.addKeyListener(this);
        this.txtVille.addKeyListener(this);
        this.txtCP.addKeyListener(this);
        this.txtExposition.addKeyListener(this);

        // Installation de la JTable
        String entetes[] = {"Nom_Immeuble", "Adresse", "CP", "Ville", "Exposition", "Surface_Habitable", "Surface_Balcon", "Capacite_Accueil", "Distance_Pistes", "Description", "Tarif"};
        this.tableauAppartements = new Tableau(this.obtenirDonnees(""), entetes);
        this.tableAppartements = new JTable(this.tableauAppartements);
        JScrollPane uneScroll = new JScrollPane(this.tableAppartements);
        uneScroll.setBounds(360, 100, 1000, 250);
        this.add(uneScroll);

        // Installation du panel Filtre
        this.panelFiltre.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.panelFiltre.setBounds(470, 60, 530, 30);
        this.panelFiltre.setLayout(new GridLayout(1, 3, 10, 10)); // Espacement entre les composants
        this.panelFiltre.add(createLabel("Filtrer les appartements par :"));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(createButton(btFiltrer));
        this.add(this.panelFiltre);
        this.btFiltrer.addActionListener(this);

        // Installation du bouton supprimer
        this.btSupprimer.setBounds(80, 340, 140, 30);
        this.add(createButton(btSupprimer));
        this.btSupprimer.addActionListener(this);
        this.btSupprimer.setVisible(false);

        // Installation du compteur
        this.lbNbClient.setBounds(700, 380, 400, 20);
        this.lbNbClient.setForeground(Color.WHITE); // Couleur de texte blanche pour contraste
        this.add(this.lbNbClient);
        this.lbNbClient.setText("Nombre d'appartements : " + this.tableauAppartements.getRowCount());

        // Rendre la table écoutable sur le clic de la souris
        this.tableAppartements.addMouseListener(this);
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
        ArrayList<Appartement> lesAppartements;
        if (filtre.equals("")) {
            lesAppartements = Controleur.selectAllAppartements();
        } else {
            lesAppartements = Controleur.selectLikeAppartements(filtre);
        }
        Object matrice[][] = new Object[lesAppartements.size()][11];
        int i = 0;
        for (Appartement unAppartement : lesAppartements) {
            matrice[i][0] = unAppartement.getNom_Immeuble();
            matrice[i][1] = unAppartement.getAdresse();
            matrice[i][2] = unAppartement.getCP();
            matrice[i][3] = unAppartement.getVille();
            matrice[i][4] = unAppartement.getExposition();
            matrice[i][5] = unAppartement.getSurface_Habitable();
            matrice[i][6] = unAppartement.getSurface_Balcon();
            matrice[i][7] = unAppartement.getCapacite_Accueil();
            matrice[i][8] = unAppartement.getDistance_Pistes();
            matrice[i][9] = unAppartement.getDescription();
            matrice[i][10] = unAppartement.getTarif();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtAdresse.setText("");
        this.txtVille.setText("");
        this.txtCP.setText("");
        this.txtExposition.setText("");

        btSupprimer.setVisible(false);
        btValider.setText("Valider");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == this.btFiltrer) {
            String filtre = txtFiltre.getText();
            this.tableauAppartements.setDonnees(this.obtenirDonnees(filtre));
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            this.traitement();
            this.lbNbClient.setText("Nombre d'appartements : " + this.tableauAppartements.getRowCount());
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            int numLigne, ID_Appartement, ID_Station, Capacite_Accueil, ID_Utilisateur, idImage;
            float Surface_Habitable, Surface_Balcon, Distance_Piste, Tarif;
            numLigne = tableAppartements.getSelectedRow();
            ID_Appartement = Integer.parseInt((tableauAppartements.getValueAt(numLigne, 0).toString()));
            Capacite_Accueil = Integer.parseInt((tableauAppartements.getValueAt(numLigne, 9).toString()));
            Surface_Habitable = Float.parseFloat(tableauAppartements.getValueAt(numLigne, 7).toString());
            Surface_Balcon = Float.parseFloat(tableauAppartements.getValueAt(numLigne, 8).toString());
            Distance_Piste = Float.parseFloat(tableauAppartements.getValueAt(numLigne, 10).toString());
            Tarif = Float.parseFloat(tableauAppartements.getValueAt(numLigne, 12).toString());
            ID_Station = Integer.parseInt(tableauAppartements.getValueAt(numLigne, 13).toString());
            ID_Utilisateur = Integer.parseInt(tableauAppartements.getValueAt(numLigne, 14).toString());
            idImage = Integer.parseInt(tableauAppartements.getValueAt(numLigne, 15).toString());

            String Nom_Immeuble = this.txtNom.getText();
            String Adresse = this.txtAdresse.getText();
            String CP = this.txtCP.getText();
            String Ville = this.txtVille.getText();
            String Exposition = this.txtExposition.getText();
            String Description = this.txtDescription.getText();

            ArrayList<String> leschamps = new ArrayList<>();
            leschamps.add(Nom_Immeuble);
            leschamps.add(Adresse);
            leschamps.add(CP);
            leschamps.add(Ville);
            leschamps.add(Exposition);
            leschamps.add(Description);

            if (Controleur.verifDonnees(leschamps)) {
                Appartement unAppartement = new Appartement(ID_Appartement, Nom_Immeuble, Adresse, CP, Ville, Exposition, Surface_Habitable, Surface_Balcon, Capacite_Accueil, Distance_Piste, Description, Tarif, ID_Station, ID_Utilisateur, idImage);
                Controleur.updateAppartement(unAppartement);
                this.tableauAppartements.setDonnees(this.obtenirDonnees(""));
                JOptionPane.showMessageDialog(this, "Modification réussie de l'appartement.", "Modification Appartement", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", "Modification de l'appartement", JOptionPane.ERROR_MESSAGE);
            }

            this.viderChamps();
        } else if (e.getSource() == btSupprimer) {
            int numLigne, idAppartement;
            numLigne = tableAppartements.getSelectedRow();
            idAppartement = Integer.parseInt((tableauAppartements.getValueAt(numLigne, 0).toString()));

            Controleur.deleteAppartement(idAppartement);
            this.tableauAppartements.setDonnees(this.obtenirDonnees(""));
            this.lbNbClient.setText("Nombre d'appartements : " + this.tableauAppartements.getRowCount());
            JOptionPane.showMessageDialog(this, "Suppression réussie de l'appartement.", "Suppression Appartement", JOptionPane.INFORMATION_MESSAGE);
            this.viderChamps();
        }
    }

    private void traitement() {
        // recuperer les données
        String nom = this.txtNom.getText();
        String prenom = this.txtAdresse.getText();
        String adresse = this.txtVille.getText();
        String email = this.txtCP.getText();
        String tel = this.txtExposition.getText();

        ArrayList<String> leschamps = new ArrayList<String>();
        leschamps.add(nom);
        leschamps.add(prenom);
        leschamps.add(adresse);
        leschamps.add(email);
        leschamps.add(tel);

        if(Controleur.verifDonnees(leschamps)) {

            // créer une instance de la classe CLient
            // Appartement unAppartement = new Appartement(nom, prenom, adresse, email, tel);

            // INserer dans la base de données
            //  Controleur.insertAppartement(unAppartement);

            // Afficher message de confirmation
            JOptionPane.showMessageDialog(this, "Insertion réussite du client.", "Insertion du client", JOptionPane.INFORMATION_MESSAGE);

            // Actualiser l'affichage du tableau client
            this.tableauAppartements.setDonnees(this.obtenirDonnees(""));
        }
        else{
            JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", "Insertion du client", JOptionPane.ERROR_MESSAGE);
        }
        // Vider les champs texte
        this.viderChamps();

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            traitement();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        int numLigne = 0;
        if (e.getClickCount() >= 1) {
            numLigne = tableAppartements.getSelectedRow();
            txtNom.setText(tableauAppartements.getValueAt(numLigne, 0).toString());
            txtAdresse.setText(tableauAppartements.getValueAt(numLigne, 1).toString());
            txtVille.setText(tableauAppartements.getValueAt(numLigne, 3).toString());
            txtCP.setText(tableauAppartements.getValueAt(numLigne, 2).toString());
            txtExposition.setText(tableauAppartements.getValueAt(numLigne, 4).toString());
            txtSurfaceHabitable.setText(tableauAppartements.getValueAt(numLigne, 5).toString());
            txtSurfaceBalcon.setText(tableauAppartements.getValueAt(numLigne, 6).toString());
            txtCapaciteAcceuil.setText(tableauAppartements.getValueAt(numLigne, 7).toString());
            txtDistancePiste.setText(tableauAppartements.getValueAt(numLigne, 8).toString());
            txtDescription.setText(tableauAppartements.getValueAt(numLigne, 9).toString());
            txtTarif.setText(tableauAppartements.getValueAt(numLigne, 10).toString());

            btSupprimer.setVisible(true);
            btValider.setText("Modifier");
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
