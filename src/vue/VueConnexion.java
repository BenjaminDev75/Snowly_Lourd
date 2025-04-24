package vue;

import controleur.Controleur;
import controleur.Snowly;
import controleur.Salarie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {
    private JButton btnAnnuler = new JButton("Annuler");
    private JButton btnSeConnecter = new JButton("Se Connecter");
    private JTextField txtEmail = new JTextField("tst@ns.com");
    private JPasswordField txtMdp = new JPasswordField("123");

    private JPanel panelForm = new JPanel();

    public VueConnexion() {
        // Changer le titre de la fenêtre
        this.setTitle("Application de gestion de Neige & Soleil");
        // Définir les dimensions de la fenêtre
        this.setBounds(100, 100, 600, 300);
        // Fermer et tuer l'application sur le bouton croix
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Changer la couleur de fond de la fenêtre
        this.getContentPane().setBackground(new Color(60, 63, 65));
        // Éliminer le quadrillage par défaut : feuille de style
        this.setLayout(null);
        // Désactiver le redimensionnement de la fenêtre
        this.setResizable(false);

        // Installation du logo
        ImageIcon uneImage = new ImageIcon("src/images/javaLog-Trans.png");
        JLabel leLogo = new JLabel(uneImage);
        leLogo.setBounds(5, 5, 250, 250);
        this.add(leLogo);

        // Construction du panel Formulaire
        this.panelForm.setBackground(new Color(60, 63, 65));
        this.panelForm.setLayout(new GridLayout(3, 2, 10, 10));
        this.panelForm.setBounds(300, 60, 250, 130);
        this.panelForm.add(createLabel("Email : "));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(createLabel("Mot de passe : "));
        this.panelForm.add(this.txtMdp);
        this.panelForm.add(createButton(btnAnnuler));
        this.panelForm.add(createButton(btnSeConnecter));
        this.add(this.panelForm);

        // Rendre le bouton cliquable
        this.btnAnnuler.addActionListener(this);
        this.btnSeConnecter.addActionListener(this);

        // Rendre les champs texte écoutables
        this.txtEmail.addKeyListener(this);
        this.txtMdp.addKeyListener(this);

        // Rendre visible la fenêtre
        this.setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton createButton(JButton button) {
        button.setBackground(new Color(75, 110, 175));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAnnuler) {
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        } else if (e.getSource() == this.btnSeConnecter) {
            this.traitement();
        }
    }

    private void traitement() {
        String email = this.txtEmail.getText();
        String mdp = new String(this.txtMdp.getPassword());

        // Récupération du technicien identifié par email et mot de passe
        Salarie unSalarie = Controleur.selectWhereSalaries(email, mdp);
        if (unSalarie == null) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un utilisateur valide.");
        } else {
            JOptionPane.showMessageDialog(this, "Bienvenue, " + unSalarie.getNom() + " !");
            // Rendre invisible la fenêtre VueConnexion
            Snowly.rendreVisibleVueConnexion(false);
            // Sauvegarde du technicien
            Snowly.setTechConnecte(unSalarie);
            // Ouvrir la vue générale
            Snowly.creerVueGenerale(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.traitement();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
