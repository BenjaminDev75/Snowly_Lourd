package vue;

import controleur.Appartement;
import controleur.Controleur;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelAppartement extends PanelPrincipal implements ActionListener, KeyListener {

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
    private JTextField txtTarif= new JTextField();


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
        super("Gestion des Appartement");

        // PLacement du panel formulaire

        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30,100, 300,200);
        this.panelForm.setLayout(new GridLayout(6,2));
        this.panelForm.add(new JLabel("Nom Immeuble :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Adresse :"));
        this.panelForm.add(this.txtAdresse);
        this.panelForm.add(new JLabel("Ville :"));
        this.panelForm.add(this.txtVille);
        this.panelForm.add(new JLabel("CP :"));
        this.panelForm.add(this.txtCP);


        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);

        // rendre les boutons ecoutable

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        this.txtNom.addKeyListener(this);
        this.txtAdresse.addKeyListener(this);
        this.txtVille.addKeyListener(this);
        this.txtCP.addKeyListener(this);
        this.txtExposition.addKeyListener(this);

        // installation de la JTable
       // String entetes[] = {"ID_Appartement", "Nom_Immeuble", "Adresse", "CP", "Ville", "Exposition", "Surface_Habitable", "Surface_Balcon", "Capacite_Accueil", "Distance_Pistes", "Description", "Tarif", "ID_Station", "ID_Utilisateur", "idImage"};
        String entetes[] = { "Nom_Immeuble", "Adresse", "CP", "Ville", "Exposition", "Surface_Habitable", "Surface_Balcon", "Capacite_Accueil", "Distance_Pistes", "Description", "Tarif"};

        this.tableauAppartements = new Tableau(this.obtenirDonnees(""),entetes);
        this.tableAppartements = new JTable(this.tableauAppartements);
        JScrollPane uneScroll = new JScrollPane(this.tableAppartements);
        uneScroll.setBounds(360,100,1000,250);
        this.add(uneScroll);

        // installation du panel Filtre
        this.panelFiltre.setBackground(Color.cyan);
        this.panelFiltre.setBounds(470,60,510,30);
        this.panelFiltre.setLayout(new GridLayout(1,3));
        this.panelFiltre.add(new JLabel("Filtrer les appartements par :"));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(this.btFiltrer);
        this.add(this.panelFiltre);
        this.btFiltrer.addActionListener(this);


        // installation du bouton supprimer
        this.btSupprimer.setBounds(80,340,140,30);
        this.add(this.btSupprimer);
        this.btSupprimer.addActionListener(this);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.red);

        // installation du compteur
        this.lbNbClient.setBounds(700,380,400,20);
        this.add(this.lbNbClient);
        this.lbNbClient.setText("Nombre de clients : " + this.tableauAppartements.getRowCount());


        // rendre la table ecoutable sur le clic de la souris
        this.tableAppartements.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                if (e.getClickCount() >=1) {
                    numLigne = tableAppartements.getSelectedRow();
                    txtNom.setText(tableauAppartements.getValueAt(numLigne,1).toString());
                    txtAdresse.setText(tableauAppartements.getValueAt(numLigne,2).toString());
                    txtVille.setText(tableauAppartements.getValueAt(numLigne,3).toString());
                    txtCP.setText(tableauAppartements.getValueAt(numLigne,4).toString());
                    txtVille.setText(tableauAppartements.getValueAt(numLigne,5).toString());
                    txtExposition.setText(tableauAppartements.getValueAt(numLigne,6).toString());
                    txtSurfaceHabitable.setText(tableauAppartements.getValueAt(numLigne,7).toString());
                    txtSurfaceBalcon.setText(tableauAppartements.getValueAt(numLigne,8).toString());
                    txtCapaciteAcceuil.setText(tableauAppartements.getValueAt(numLigne,9).toString());
                    txtDistancePiste.setText(tableauAppartements.getValueAt(numLigne,10).toString());
                    txtDescription.setText(tableauAppartements.getValueAt(numLigne,11).toString());
                    txtTarif.setText(tableauAppartements.getValueAt(numLigne,12).toString());

                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
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
        ArrayList<Appartement> lesAppartements;
        if(filtre.equals("")){
            lesAppartements = Controleur.selectAllAppartements();
        }
        else {
            lesAppartements = Controleur.selectLikeAppartements(filtre);
        }
        Object matrice[][] = new Object[lesAppartements.size()][11];
        int i =0;
        for(Appartement unAppartement : lesAppartements){
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

    // vide les champs
    public void viderChamps(){
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

        if(e.getSource() == this.btAnnuler) {
            this.viderChamps();
        }
        else if(e.getSource() == this.btFiltrer) {
            // on récupère le filtre
            String filtre = txtFiltre.getText();

            // on actualise les données
            this.tableauAppartements.setDonnees(this.obtenirDonnees(filtre));

        }
        else if(e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            this.traitement();
            this.lbNbClient.setText("Nombre d'appartement : " + this.tableauAppartements.getRowCount());
        }
        else if(e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            // recuperer l'id client
            int numLigne, ID_Appartement,ID_Station,Capacite_Accueil,
            ID_Utilisateur,
                    idImage;
            float Surface_Habitable, Surface_Balcon, Distance_Piste,Tarif;
            numLigne = tableAppartements.getSelectedRow();
            ID_Appartement = Integer.parseInt((tableauAppartements.getValueAt(numLigne,0).toString()));
            Capacite_Accueil = Integer.parseInt((tableauAppartements.getValueAt(numLigne,9).toString()));


            Surface_Habitable = Float.parseFloat(tableauAppartements.getValueAt(numLigne,7).toString());
            Surface_Balcon = Float.parseFloat(tableauAppartements.getValueAt(numLigne,8).toString());
            Distance_Piste = Float.parseFloat(tableauAppartements.getValueAt(numLigne,10).toString());

            Tarif = Float.parseFloat(tableauAppartements.getValueAt(numLigne,12).toString());

            ID_Station = Integer.parseInt(tableauAppartements.getValueAt(numLigne,13).toString());
            ID_Utilisateur = Integer.parseInt(tableauAppartements.getValueAt(numLigne,14).toString());
            idImage = Integer.parseInt(tableauAppartements.getValueAt(numLigne,15).toString());



            // recuperer les champs de données
            String Nom_Immeuble = this.txtNom.getText();
            String Adresse = this.txtAdresse.getText();
            String CP = this.txtCP.getText();
            String Ville = this.txtVille.getText();
            String Exposition = this.txtExposition.getText();
            String Description = this.txtDescription.getText();


            ArrayList<String> leschamps = new ArrayList<String>();
            leschamps.add(Nom_Immeuble);
            leschamps.add(Adresse);
            leschamps.add(CP);
            leschamps.add(Ville);
            leschamps.add(Exposition);




            leschamps.add(Description);


            if(Controleur.verifDonnees(leschamps)) {
                //instancier un nouveua client
                Appartement unAppartement = new Appartement(ID_Appartement,Nom_Immeuble,Adresse,CP,Ville,Exposition,Surface_Habitable,Surface_Balcon,Capacite_Accueil,Distance_Piste,Description,Tarif,ID_Station, ID_Utilisateur, idImage);
                // realiser la modification dans la bdd
                Controleur.updateAppartement(unAppartement);
                //actualiser l'affichage
                this.tableauAppartements.setDonnees(this.obtenirDonnees(""));
                // message de confirmation
                JOptionPane.showMessageDialog(this,"Modification réussite du client.","Modification CLient",JOptionPane.INFORMATION_MESSAGE);

            }else{
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", "Insertion du client", JOptionPane.ERROR_MESSAGE);
            }

            // vider les champs
            this.viderChamps();
        }
        else if(e.getSource() == btSupprimer){
            // on recupère l'id du client
            int numLigne, idClient;
            numLigne = tableAppartements.getSelectedRow();
            idClient = Integer.parseInt((tableauAppartements.getValueAt(numLigne,0).toString()));

            // on supprime dans la bdd
            Controleur.deleteAppartement(idClient);

            // on actualise l'affichage
            this.tableauAppartements.setDonnees(this.obtenirDonnees(""));
            this.lbNbClient.setText("Nombre de clients : " + this.tableauAppartements.getRowCount());

            // confirmation suppression réussi
            JOptionPane.showMessageDialog(this,"Suppression réussite du client.","Suppression CLient",JOptionPane.INFORMATION_MESSAGE);

            // vider les champs
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            traitement();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
