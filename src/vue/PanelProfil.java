package vue;

import controleur.Controleur;
import controleur.Orange;
import controleur.Salarie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelProfil extends PanelPrincipal implements ActionListener {

    private JTextArea textInfos = new JTextArea();
    private Salarie unSalarie;
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();

    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp1 = new JPasswordField();
    private JPasswordField txtMdp2 = new JPasswordField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    public PanelProfil() {
        super("Gestion du Profil");

        // installation du panel Form
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(650,100,400,300);
        this.panelForm.setLayout(new GridLayout(8,2));
        this.panelForm.setVisible(false);
        this.panelForm.add(new JLabel("Nom Utilisateur : "));
        this.panelForm.add(txtNom);
        this.panelForm.add(new JLabel("Prenom  Utilisateur: "));
        this.panelForm.add(txtPrenom);
        this.panelForm.add(new JLabel("Email Utilisateur : "));
        this.panelForm.add(txtEmail);
        this.panelForm.add(new JLabel("MDP : "));
        this.panelForm.add(txtMdp1);
        this.panelForm.add(new JLabel("Confirmation MDP : "));
        this.panelForm.add(txtMdp2);


        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);



        // installation de TextArea
        this.textInfos.setBounds(300,100,300,150);
        unSalarie = Orange.getTechConnecte();
        this.textInfos.setEditable(false);
        this.textInfos.setBackground(Color.cyan);

        this.textInfos.setText("_________________INFO PROFIL_______________\n\n"
                                    + "Nom Utilisateur : " + unSalarie.getNom() + "\n\n"
                                    + "Prenom Utilisateur : " + unSalarie.getPrenom() + "\n\n"
                                    + "Email Utilisateur : " + unSalarie.getEmail() + "\n"
                                    + "__________________________________________________________");
        this.add(textInfos);

        this.btModifier.setBounds(350,286,200,40);
        this.add(this.btModifier);

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        this.btModifier.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btModifier) {
            this.txtNom.setText(unSalarie.getNom());
            this.txtPrenom.setText(unSalarie.getPrenom());
            this.txtEmail.setText(unSalarie.getEmail());
            this.panelForm.setVisible(true);
        }
        else if (e.getSource() == this.btAnnuler) {
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtEmail.setText("");
            this.txtMdp1.setText("");
            this.txtMdp2.setText("");


            this.panelForm.setVisible(false);
        }
        else if (e.getSource() == this.btValider) {
            // on recupère les champs
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String mdp1 = new String( this.txtMdp1.getPassword());
            String mdp2 = new String( this.txtMdp2.getPassword());
            String email = this.txtEmail.getText();

            ArrayList<String> leschamps = new ArrayList<String>();
            leschamps.add(nom);
            leschamps.add(prenom);
            leschamps.add(mdp1);
            leschamps.add(mdp2);
            leschamps.add(email);

            // on verifie si les champs sont remplis et on verifie l'égalité des mdp's
            if(Controleur.verifDonnees(leschamps) && mdp1.equals(mdp2)) {

            // on instancie le unSalarie
                unSalarie.setNom(nom);
                unSalarie.setPrenom(prenom);
                unSalarie.setEmail(email);


            // on modifie dans la bdd
            Controleur.updateSalarie(unSalarie);

            // on modifie le technicien connecte
            Orange.setTechConnecte(unSalarie);

            // message de confirmation
            JOptionPane.showMessageDialog(this, "Modification réussite du technicien.",
                    "Modification du technicien", JOptionPane.INFORMATION_MESSAGE);
                this.textInfos.setText("_________________INFO PROFIL_______________\n\n"
                        + "Nom Utilisateur : " + unSalarie.getNom() + "\n\n"
                        + "Prenom Utilisateur : " + unSalarie.getPrenom() + "\n\n"
                        + "Email Utilisateur: " + unSalarie.getEmail() + "\n"
                        + "__________________________________________________________");
            }
            else{
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.",
                        "Modification du technicien", JOptionPane.ERROR_MESSAGE);
            }
            // on vide les données
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtEmail.setText("");
            this.txtMdp1.setText("");
            this.txtMdp2.setText("");

            // on rend le formulaire invisible
            this.panelForm.setVisible(false);
        }
    }
}
