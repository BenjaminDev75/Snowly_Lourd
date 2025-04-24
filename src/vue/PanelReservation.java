package vue;

import controleur.Reservation;
import controleur.Controleur;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelReservation extends PanelPrincipal implements ActionListener, KeyListener, MouseListener {

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

        // Placement du panel formulaire
        this.panelForm.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.panelForm.setBounds(30, 100, 300, 200);
        this.panelForm.setLayout(new GridLayout(7, 2, 10, 10)); // Espacement entre les composants
        this.panelForm.add(createLabel("Date Réservation :"));
        this.panelForm.add(this.txtDateReservation);
        this.panelForm.add(createLabel("Date Début :"));
        this.panelForm.add(this.txtDateDebut);
        this.panelForm.add(createLabel("Date Fin :"));
        this.panelForm.add(this.txtDateFin);
        this.panelForm.add(createLabel("Montant Total :"));
        this.panelForm.add(this.txtMontantTotal);
        this.panelForm.add(createLabel("ID Utilisateur :"));
        this.panelForm.add(this.txtIDUtilisateur);
        this.panelForm.add(createLabel("ID Appartement :"));
        this.panelForm.add(this.txtIDAppartement);
        this.panelForm.add(createButton(btAnnuler));
        this.panelForm.add(createButton(btValider));
        this.add(this.panelForm);

        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Installation de la JTable
        String entetes[] = {"ID Réservation", "Date Réservation", "Date Début", "Date Fin", "Montant Total", "ID Utilisateur", "ID Appartement"};
        this.tableauReservations = new Tableau(this.obtenirDonnees(), entetes);
        this.tableReservations = new JTable(this.tableauReservations);
        JScrollPane uneScroll = new JScrollPane(this.tableReservations);
        uneScroll.setBounds(360, 100, 800, 250);
        this.add(uneScroll);

        // Installation du bouton supprimer
        this.btSupprimer.setBounds(80, 220, 140, 30);
        this.add(createButton(btSupprimer));
        this.btSupprimer.addActionListener(this);
        this.btSupprimer.setVisible(false);

        // Rendre la table écoutable sur le clic de la souris
        this.tableReservations.addMouseListener(this);

        // Installation du compteur
        this.lbNbReservation.setBounds(770, 380, 400, 20);
        this.lbNbReservation.setForeground(Color.WHITE); // Couleur de texte blanche pour contraste
        this.add(this.lbNbReservation);
        this.lbNbReservation.setText("Nombre de réservations : " + this.tableauReservations.getRowCount());
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
        }
    }

    private void traitement() {
        String dateReservation = this.txtDateReservation.getText();
        String dateDebut = this.txtDateDebut.getText();
        String dateFin = this.txtDateFin.getText();
        float montantTotal = Float.parseFloat(this.txtMontantTotal.getText());
        int idUtilisateur = Integer.parseInt(this.txtIDUtilisateur.getText());
        int idAppartement = Integer.parseInt(this.txtIDAppartement.getText());

        Reservation uneReservation = new Reservation(0, dateReservation, dateDebut, dateFin, montantTotal, idUtilisateur, idAppartement);
        Controleur.insertReservation(uneReservation);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int numLigne = tableReservations.getSelectedRow();
        if (numLigne != -1) {
            txtDateReservation.setText(tableauReservations.getValueAt(numLigne, 1).toString());
            txtDateDebut.setText(tableauReservations.getValueAt(numLigne, 2).toString());
            txtDateFin.setText(tableauReservations.getValueAt(numLigne, 3).toString());
            txtMontantTotal.setText(tableauReservations.getValueAt(numLigne, 4).toString());
            txtIDUtilisateur.setText(tableauReservations.getValueAt(numLigne, 5).toString());
            txtIDAppartement.setText(tableauReservations.getValueAt(numLigne, 6).toString());
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
