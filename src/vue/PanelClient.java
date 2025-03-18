package vue;

import controleur.Client;
import controleur.Controleur;
import controleur.Tableau;

import javax.security.auth.Refreshable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelClient extends PanelPrincipal implements ActionListener, KeyListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtTel = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    private JTable tableClients;
    private Tableau tableauClients;

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JButton btSupprimer = new JButton("Supprimer");

    private JLabel lbNbClient = new JLabel();


    public PanelClient() {
        super("Gestion des Client");

        // PLacement du panel formulaire

        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(150,120, 300,200);
        this.panelForm.setLayout(new GridLayout(6,2));
        this.panelForm.add(new JLabel("Nom Client :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Prenom Client :"));
        this.panelForm.add(this.txtPrenom);
        this.panelForm.add(new JLabel("Adresse Client :"));
        this.panelForm.add(this.txtAdresse);
        this.panelForm.add(new JLabel("Email Client :"));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(new JLabel("Tel Client :"));
        this.panelForm.add(this.txtTel);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);


        // rendre les boutons ecoutable

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        this.txtNom.addKeyListener(this);
        this.txtPrenom.addKeyListener(this);
        this.txtAdresse.addKeyListener(this);
        this.txtEmail.addKeyListener(this);
        this.txtTel.addKeyListener(this);

        // installation de la JTable
        String entetes[] = {"ID_Utilisateur","Id Client","Nom","Prenom","Email","Adresse","Téléphone"};

        this.tableauClients = new Tableau(this.obtenirDonnees(""),entetes);
        this.tableClients = new JTable(this.tableauClients);
        JScrollPane uneScroll = new JScrollPane(this.tableClients);
        uneScroll.setBounds(500,100,700,250);
        this.add(uneScroll);

        // installation du panel Filtre
        this.panelFiltre.setBackground(Color.cyan);
        this.panelFiltre.setBounds(570,60,450,30);
        this.panelFiltre.setLayout(new GridLayout(1,3));
        this.panelFiltre.add(new JLabel("Filtrer les clients par :"));
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
        this.lbNbClient.setBounds(770,380,400,20);
        this.add(this.lbNbClient);
        this.lbNbClient.setText("Nombre de clients : " + this.tableauClients.getRowCount());


        // rendre la table ecoutable sur le clic de la souris
        this.tableClients.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                if (e.getClickCount() >=1) {
                    numLigne = tableClients.getSelectedRow();
                    txtNom.setText(tableauClients.getValueAt(numLigne,2).toString());
                    txtPrenom.setText(tableauClients.getValueAt(numLigne,3).toString());
                    txtAdresse.setText(tableauClients.getValueAt(numLigne,4).toString());
                    txtEmail.setText(tableauClients.getValueAt(numLigne,5).toString());
                    if (tableauClients.getValueAt(numLigne, 6) != null) {
                        txtTel.setText(tableauClients.getValueAt(numLigne, 6).toString());
                    } else {
                        txtTel.setText("");
                    }
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
        ArrayList<Client> lesClients;
        if(filtre.equals("")){
            lesClients = Controleur.selectAllClients();
        }
        else {
            lesClients = Controleur.selectLikeClients(filtre);
        }
        Object matrice[][] = new Object[lesClients.size()][7];
        int i =0;
        for(Client unClient : lesClients){
            matrice[i][0] = unClient.getID_Utilisateur();
            matrice[i][1] = unClient.getIdClient();
            matrice[i][2] = unClient.getNom();
            matrice[i][3] = unClient.getPrenom();
            matrice[i][4] = unClient.getAdresse();
            matrice[i][5] = unClient.getEmail();
            matrice[i][6] = unClient.getTelephone();
            i++;
        }
        return matrice;
    }

    // vide les champs
    public void viderChamps(){
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtAdresse.setText("");
        this.txtEmail.setText("");
        this.txtTel.setText("");

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
            this.tableauClients.setDonnees(this.obtenirDonnees(filtre));

        }
        else if(e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            this.traitement();
            this.lbNbClient.setText("Nombre de clients : " + this.tableauClients.getRowCount());
        }
        else if(e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            // recuperer l'id client
            int numLigne, idClient,ID_Utilisateur;
            numLigne = tableClients.getSelectedRow();
            ID_Utilisateur = Integer.parseInt((tableauClients.getValueAt(numLigne,0).toString()));
            idClient = Integer.parseInt((tableauClients.getValueAt(numLigne,1).toString()));


            // recuperer les champs de données
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String adresse = this.txtAdresse.getText();
            String email = this.txtEmail.getText();
            String tel = this.txtTel.getText();

            ArrayList<String> leschamps = new ArrayList<String>();
            leschamps.add(nom);
            leschamps.add(prenom);
            leschamps.add(adresse);
            leschamps.add(email);
            leschamps.add(tel);

            if(Controleur.verifDonnees(leschamps)) {
                //instancier un nouveua client
                Client unClient = new Client(ID_Utilisateur,idClient,nom,prenom,adresse,email,tel);
                // realiser la modification dans la bdd
                Controleur.updateClient(unClient);
                //actualiser l'affichage
                this.tableauClients.setDonnees(this.obtenirDonnees(""));
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
            numLigne = tableClients.getSelectedRow();
            idClient = Integer.parseInt((tableauClients.getValueAt(numLigne,0).toString()));

            // on supprime dans la bdd
            Controleur.deleteClient(idClient);

            // on actualise l'affichage
            this.tableauClients.setDonnees(this.obtenirDonnees(""));
            this.lbNbClient.setText("Nombre de clients : " + this.tableauClients.getRowCount());

            // confirmation suppression réussi
            JOptionPane.showMessageDialog(this,"Suppression réussite du client.","Suppression CLient",JOptionPane.INFORMATION_MESSAGE);

            // vider les champs
            this.viderChamps();
        }

    }

    private void traitement() {
        // recuperer les données
        String nom = this.txtNom.getText();
        String prenom = this.txtPrenom.getText();
        String adresse = this.txtAdresse.getText();
        String email = this.txtEmail.getText();
        String tel = this.txtTel.getText();

        ArrayList<String> leschamps = new ArrayList<String>();
        leschamps.add(nom);
        leschamps.add(prenom);
        leschamps.add(adresse);
        leschamps.add(email);
        leschamps.add(tel);

        if(Controleur.verifDonnees(leschamps)) {

            // créer une instance de la classe CLient
            Client unClient = new Client(nom, prenom, adresse, email, tel);

            // INserer dans la base de données
            Controleur.insertClient(unClient);

            // Afficher message de confirmation
            JOptionPane.showMessageDialog(this, "Insertion réussite du client.", "Insertion du client", JOptionPane.INFORMATION_MESSAGE);

            // Actualiser l'affichage du tableau client
            this.tableauClients.setDonnees(this.obtenirDonnees(""));
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
