package vue;

import controleur.Reservation;
import controleur.Controleur;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelReservation extends PanelPrincipal implements ActionListener, KeyListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtDateReservation = new JTextField();
    private JTextField txtDateDebut = new JTextField();
    private JTextField txtDateFin = new JTextField();
    private JTextField txtMontantTotal = new JTextField();
    private JTextField txtIDUtilisateur = new JTextField();
    private JTextField txtIDAppartement = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    private JTable tableReservations;
    private Tableau tableauReservations;

    private JButton btSupprimer = new JButton("Supprimer");
    private JLabel lbNbReservation = new JLabel();


    public PanelReservation() {
        super("Gestion des Réservations");

        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30, 100, 300, 200);
        this.panelForm.setLayout(new GridLayout(7, 2));
        this.panelForm.add(new JLabel("Date Réservation :"));
        this.panelForm.add(this.txtDateReservation);
        this.panelForm.add(new JLabel("Date Début :"));
        this.panelForm.add(this.txtDateDebut);
        this.panelForm.add(new JLabel("Date Fin :"));
        this.panelForm.add(this.txtDateFin);
        this.panelForm.add(new JLabel("Montant Total :"));
        this.panelForm.add(this.txtMontantTotal);
        this.panelForm.add(new JLabel("ID Utilisateur :"));
        this.panelForm.add(this.txtIDUtilisateur);
        this.panelForm.add(new JLabel("ID Appartement :"));
        this.panelForm.add(this.txtIDAppartement);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
    //  this.add(this.panelForm);

      //  this.btAnnuler.addActionListener(this);
      //  this.btValider.addActionListener(this);

        String entetes[] = {"ID Réservation", "Date Réservation", "Date Début", "Date Fin", "Montant Total", "ID Utilisateur", "ID Appartement"};
        this.tableauReservations = new Tableau(this.obtenirDonnees(), entetes);
        this.tableReservations = new JTable(this.tableauReservations);
        JScrollPane uneScroll = new JScrollPane(this.tableReservations);
        uneScroll.setBounds(360, 100, 800, 250);
        this.add(uneScroll);

        this.btSupprimer.setBounds(80, 220, 140, 30);
        this.add(this.btSupprimer);
        this.btSupprimer.addActionListener(this);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.red);

        // Rendre la table écoutable sur le clic de la souris
        this.tableReservations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = tableReservations.getSelectedRow();
                if (numLigne != -1) {
                    btSupprimer.setVisible(true);
                }
            }
        });
        // Installation du compteur
        this.lbNbReservation.setBounds(770, 380, 400, 20);
        this.add(this.lbNbReservation);
        this.lbNbReservation.setText("Nombre de réservations : " + this.tableauReservations.getRowCount());

    }



    public Object[][] obtenirDonnees() {
        ArrayList<Reservation> lesReservations = Controleur.selectAllReservations();
        Object matrice[][] = new Object[lesReservations.size()][7];
        int i = 0;
        for (Reservation uneReservation : lesReservations) {
            matrice[i][0] = uneReservation.getID_Reservation();
            matrice[i][1] = uneReservation.getDateReservation();
            matrice[i][2] = uneReservation.getDateDebut();
            matrice[i][3] = uneReservation.getDateFin();
            matrice[i][4] = uneReservation.getMontant_Total();
            matrice[i][5] = uneReservation.getID_Utilisateur();
            matrice[i][6] = uneReservation.getID_Appartement();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtDateReservation.setText("");
        this.txtDateDebut.setText("");
        this.txtDateFin.setText("");
        this.txtMontantTotal.setText("");
        this.txtIDUtilisateur.setText("");
        this.txtIDAppartement.setText("");
        this.btSupprimer.setVisible(false);
        this.btValider.setText("Valider");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == this.btValider) {
            this.traitement();
        } else if (e.getSource() == btSupprimer) {
            int numLigne = tableReservations.getSelectedRow();
            int idReservation = Integer.parseInt(tableauReservations.getValueAt(numLigne, 0).toString());
            Controleur.deleteReservation(idReservation);
            this.tableauReservations.setDonnees(this.obtenirDonnees());
            JOptionPane.showMessageDialog(this, "Suppression réussie de la réservation.", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            this.viderChamps();
        }else if (e.getSource() == btSupprimer) {
            int numLigne = tableReservations.getSelectedRow();
            if (numLigne != -1) {
                int idReservation = Integer.parseInt(tableauReservations.getValueAt(numLigne, 0).toString());

                // Suppression dans la base de données
                Controleur.deleteReservation(idReservation);

                // Actualisation de l'affichage
               // this.tableauReservations.setDonnees(this.obtenirDonnees(""));
                this.tableauReservations.setDonnees(this.obtenirDonnees());
                this.lbNbReservation.setText("Nombre de réservations : " + this.tableauReservations.getRowCount());

                // Message de confirmation
                JOptionPane.showMessageDialog(this, "Suppression réussie de la réservation.", "Suppression Réservation", JOptionPane.INFORMATION_MESSAGE);

                // Cacher le bouton supprimer
                btSupprimer.setVisible(false);
            }
        }

    }

    private void traitement() {
        String dateReservation = this.txtDateReservation.getText();
        String dateDebut = this.txtDateDebut.getText();
        String dateFin = this.txtDateFin.getText();
        double montantTotal = Double.parseDouble(this.txtMontantTotal.getText());
        int idUtilisateur = Integer.parseInt(this.txtIDUtilisateur.getText());
        int idAppartement = Integer.parseInt(this.txtIDAppartement.getText());

       // Reservation uneReservation = new Reservation(0, dateReservation, dateDebut, dateFin, montantTotal, idUtilisateur, idAppartement);
        //Controleur.insertReservation(uneReservation);
        this.tableauReservations.setDonnees(this.obtenirDonnees());
        JOptionPane.showMessageDialog(this, "Insertion réussie de la réservation.", "Insertion", JOptionPane.INFORMATION_MESSAGE);
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
}
