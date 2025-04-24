package vue;

import controleur.Client;
import controleur.Controleur;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelClient extends PanelPrincipal implements ActionListener, KeyListener, MouseListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtVille = new JTextField();
    private JTextField txtCP = new JTextField();
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
        super("Gestion des Clients");

        // Placement du panel formulaire
        this.panelForm.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.panelForm.setBounds(150, 120, 300, 200);
        this.panelForm.setLayout(new GridLayout(8, 2, 10, 10)); // Espacement entre les composants
        this.panelForm.add(createLabel("Nom Client :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(createLabel("Prénom Client :"));
        this.panelForm.add(this.txtPrenom);
        this.panelForm.add(createLabel("Adresse Client :"));
        this.panelForm.add(this.txtAdresse);
        this.panelForm.add(createLabel("Ville Client :"));
        this.panelForm.add(this.txtVille);
        this.panelForm.add(createLabel("CP Client :"));
        this.panelForm.add(this.txtCP);
        this.panelForm.add(createLabel("Email Client :"));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(createLabel("Téléphone Client :"));
        this.panelForm.add(this.txtTel);
        this.panelForm.add(createButton(btAnnuler));
        this.panelForm.add(createButton(btValider));
        this.add(this.panelForm);

        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        this.txtNom.addKeyListener(this);
        this.txtPrenom.addKeyListener(this);
        this.txtAdresse.addKeyListener(this);
        this.txtVille.addKeyListener(this);
        this.txtCP.addKeyListener(this);
        this.txtEmail.addKeyListener(this);
        this.txtTel.addKeyListener(this);

        // Installation de la JTable
        String entetes[] = {"ID_Utilisateur", "Id Client", "Nom", "Prénom", "Email" ,"Adresse","Ville","CP", "Téléphone"};
        this.tableauClients = new Tableau(this.obtenirDonnees(""), entetes);
        this.tableClients = new JTable(this.tableauClients);
        JScrollPane uneScroll = new JScrollPane(this.tableClients);
        uneScroll.setBounds(500, 100, 700, 250);
        this.add(uneScroll);

        // Installation du panel Filtre
        this.panelFiltre.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.panelFiltre.setBounds(570, 60, 450, 30);
        this.panelFiltre.setLayout(new GridLayout(1, 3, 10, 10)); // Espacement entre les composants
        this.panelFiltre.add(createLabel("Filtrer les clients par :"));
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
        this.lbNbClient.setBounds(770, 380, 400, 20);
        this.lbNbClient.setForeground(Color.WHITE); // Couleur de texte blanche pour contraste
        this.add(this.lbNbClient);
        this.lbNbClient.setText("Nombre de clients : " + this.tableauClients.getRowCount());

        // Rendre la table écoutable sur le clic de la souris
        this.tableClients.addMouseListener(this);
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
        ArrayList<Client> lesClients;
        if (filtre.equals("")) {
            lesClients = Controleur.selectAllClients();
        } else {
            lesClients = Controleur.selectLikeClients(filtre);
        }
        Object matrice[][] = new Object[lesClients.size()][9];
        int i = 0;
        for (Client unClient : lesClients) {
            matrice[i][0] = unClient.getID_Utilisateur();
            matrice[i][1] = unClient.getIdClient();
            matrice[i][2] = unClient.getNom();
            matrice[i][3] = unClient.getPrenom();
            matrice[i][4] = unClient.getAdresse();
            matrice[i][5] = unClient.getEmail();
            matrice[i][6] = unClient.getVille();
            matrice[i][7] = unClient.getCp();
            matrice[i][8] = unClient.getTelephone();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtAdresse.setText("");
        this.txtVille.setText("");
        this.txtCP.setText("");
        this.txtEmail.setText("");
        this.txtTel.setText("");

        btSupprimer.setVisible(false);
        btValider.setText("Valider");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == this.btFiltrer) {
            String filtre = txtFiltre.getText();
            this.tableauClients.setDonnees(this.obtenirDonnees(filtre));
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            this.traitement();
            this.lbNbClient.setText("Nombre de clients : " + this.tableauClients.getRowCount());
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            int numLigne, idClient, ID_Utilisateur;
            numLigne = tableClients.getSelectedRow();
            ID_Utilisateur = Integer.parseInt((tableauClients.getValueAt(numLigne, 0).toString()));
            idClient = Integer.parseInt((tableauClients.getValueAt(numLigne, 1).toString()));

            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String adresse = this.txtAdresse.getText();
            String ville = this.txtVille.getText();
            String cp = this.txtCP.getText();
            String email = this.txtEmail.getText();
            String tel = this.txtTel.getText();

            ArrayList<String> leschamps = new ArrayList<>();
            leschamps.add(nom);
            leschamps.add(prenom);
            leschamps.add(adresse);
            leschamps.add(ville);
            leschamps.add(cp);
            leschamps.add(email);
            leschamps.add(tel);


            if (Controleur.verifDonnees(leschamps)) {
                Client unClient = new Client(ID_Utilisateur, idClient, nom, prenom, email,adresse,ville,cp, tel);
                Controleur.updateClient(unClient);
                this.tableauClients.setDonnees(this.obtenirDonnees(""));
                JOptionPane.showMessageDialog(this, "Modification réussie du client.", "Modification Client", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", "Insertion du client", JOptionPane.ERROR_MESSAGE);
            }

            this.viderChamps();
        } else if (e.getSource() == btSupprimer) {
            int numLigne, idClient;
            numLigne = tableClients.getSelectedRow();
            idClient = Integer.parseInt((tableauClients.getValueAt(numLigne, 0).toString()));

            Controleur.deleteClient(idClient);
            this.tableauClients.setDonnees(this.obtenirDonnees(""));
            this.lbNbClient.setText("Nombre de clients : " + this.tableauClients.getRowCount());
            JOptionPane.showMessageDialog(this, "Suppression réussie du client.", "Suppression Client", JOptionPane.INFORMATION_MESSAGE);
            this.viderChamps();
        }
    }

    private void traitement() {
        String nom = this.txtNom.getText();
        String prenom = this.txtPrenom.getText();
        String adresse = this.txtAdresse.getText();
        String ville = this.txtVille.getText();
        String cp = this.txtCP.getText();
        String email = this.txtEmail.getText();
        String tel = this.txtTel.getText();

        ArrayList<String> leschamps = new ArrayList<>();
        leschamps.add(nom);
        leschamps.add(prenom);
        leschamps.add(ville);
        leschamps.add(cp);
        leschamps.add(adresse);
        leschamps.add(email);
        leschamps.add(tel);

        if (Controleur.verifDonnees(leschamps)) {
            Client unClient = new Client( nom, prenom, email,adresse,ville,cp, tel);
            Controleur.insertClient(unClient);
            JOptionPane.showMessageDialog(this, "Insertion réussie du client.", "Insertion du client", JOptionPane.INFORMATION_MESSAGE);
            this.tableauClients.setDonnees(this.obtenirDonnees(""));
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", "Insertion du client", JOptionPane.ERROR_MESSAGE);
        }
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
            numLigne = tableClients.getSelectedRow();
            txtNom.setText(tableauClients.getValueAt(numLigne, 2).toString());
            txtPrenom.setText(tableauClients.getValueAt(numLigne, 3).toString());
            txtAdresse.setText(tableauClients.getValueAt(numLigne, 5).toString());
            txtVille.setText(this.tableauClients.getValueAt(numLigne, 6).toString());
            txtCP.setText(this.tableauClients.getValueAt(numLigne, 7).toString());
            txtEmail.setText(tableauClients.getValueAt(numLigne, 4).toString());
            if (tableauClients.getValueAt(numLigne, 8) != null) {
                txtTel.setText(tableauClients.getValueAt(numLigne, 8).toString());
            } else {
                txtTel.setText("");
            }
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
