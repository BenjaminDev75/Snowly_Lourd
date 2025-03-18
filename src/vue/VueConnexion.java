package vue;

import com.sun.tools.jconsole.JConsoleContext;
import controleur.Controleur;
import controleur.Orange;
import controleur.Proprietaire;
import controleur.Salarie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class VueConnexion extends JFrame  implements ActionListener, KeyListener {
        private JButton btnAnnuler = new JButton("Annuler");
        private JButton btnSeConnecter = new JButton("Se Connecter");
        private JTextField txtEmail = new JTextField("tst@ns.com");
        private JPasswordField txtMdp = new JPasswordField("123");

        private JPanel panelForm = new JPanel();

    public VueConnexion() {

        // changer le titre de la fenêtre
        this.setTitle("Application de gestion de Neige & Soleil");
        // definir des dimension de la fenêtre
        this.setBounds(100, 100, 600, 300);

        // fermer et tuer l'application sur le bouton croix
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // changer de couleur de fond de la fenêtre
        this.getContentPane().setBackground(Color.lightGray);
        //this.getContentPane().setBackground(new Color(13,52,52));

        // eliminer le quadrillage par default : feuille de style
        this.setLayout(null);

        // desactiver le redimensionnnement de la fenêtre
        this.setResizable(false);

        // installation du logo
        ImageIcon uneImage = new ImageIcon("src/images/javaLog-Trans.png");
        JLabel leLogo = new JLabel(uneImage);
        leLogo.setBounds(5, 5, 250, 250);
        this.add(leLogo);


        // construction du panel Formulaire
        this.panelForm.setBackground(Color.lightGray);

        this.panelForm.setLayout(new GridLayout(3, 2));
        this.panelForm.setBounds(300,60,250,130);
        this.panelForm.add(new JLabel("Email : "));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(new JLabel("Mdp : "));
        this.panelForm.add(this.txtMdp);
        this.panelForm.add(btnAnnuler);
        this.panelForm.add(btnSeConnecter);
        this.add(this.panelForm);


        // rendre le btn cliquable
        this.btnAnnuler.addActionListener(this);
        this.btnSeConnecter.addActionListener(this);

        // rendre les champs txt ecoutable
        this.txtEmail.addKeyListener(this);
        this.txtMdp.addKeyListener(this);


        // rendre visible la fenêtre
        this.setVisible(true);

    }

    @Override // n'as pas le droit de changer la methode !!!
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAnnuler) {
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        }
        else if (e.getSource() == this.btnSeConnecter) {
           this.traitement();
        }
    }

    private void traitement() {
        String email = this.txtEmail.getText();
        String mdp = new String(this.txtMdp.getPassword());

        // recuperation du technicien identifier par email et mdp
        Salarie unSalarie = Controleur.selectWhereSalaries(email,mdp);
        if(unSalarie == null) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un Utilisateur");
            System.out.println(email);
        }else{
            JOptionPane.showMessageDialog(this, "Bienvenue. "+unSalarie.getNom() + " !");

            // rendre invisible la fenetre VueConnexion
            Orange.rendreVisibleVueConnexion(false);
            // save de technicien
            Orange.setTechConnecte(unSalarie);
            // ouvrir la vueGenerale
            Orange.creerVueGenerale(true);


        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
           this.traitement();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
