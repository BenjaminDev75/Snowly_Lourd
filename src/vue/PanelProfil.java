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

        // Installation du panel Form
        this.panelForm.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.panelForm.setBounds(650, 100, 400, 300);
        this.panelForm.setLayout(new GridLayout(8, 2, 10, 10)); // Espacement entre les composants
        this.panelForm.setVisible(false);
        this.panelForm.add(createLabel("Nom Utilisateur : "));
        this.panelForm.add(txtNom);
        this.panelForm.add(createLabel("Prénom Utilisateur : "));
        this.panelForm.add(txtPrenom);
        this.panelForm.add(createLabel("Email Utilisateur : "));
        this.panelForm.add(txtEmail);
        this.panelForm.add(createLabel("Mot de passe : "));
        this.panelForm.add(txtMdp1);
        this.panelForm.add(createLabel("Confirmation Mot de passe : "));
        this.panelForm.add(txtMdp2);
        this.panelForm.add(createButton(btAnnuler));
        this.panelForm.add(createButton(btValider));
        this.add(this.panelForm);

        // Installation de TextArea
        this.textInfos.setBounds(300, 100, 300, 150);
        unSalarie = Orange.getTechConnecte();
        this.textInfos.setEditable(false);
        this.textInfos.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.textInfos.setForeground(Color.WHITE); // Couleur de texte blanche pour contraste
        this.textInfos.setText("_________________INFO PROFIL_______________\n\n"
                + "Nom Utilisateur : " + unSalarie.getNom() + "\n\n"
                + "Prénom Utilisateur : " + unSalarie.getPrenom() + "\n\n"
                + "Email Utilisateur : " + unSalarie.getEmail() + "\n"
                + "__________________________________________________________");
        this.add(textInfos);

        this.btModifier.setBounds(350, 286, 200, 40);
        this.add(createButton(btModifier));

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        this.btModifier.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btModifier) {
            this.txtNom.setText(unSalarie.getNom());
            this.txtPrenom.setText(unSalarie.getPrenom());
            this.txtEmail.setText(unSalarie.getEmail());
            this.panelForm.setVisible(true);
        } else if (e.getSource() == this.btAnnuler) {
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtEmail.setText("");
            this.txtMdp1.setText("");
            this.txtMdp2.setText("");
            this.panelForm.setVisible(false);
        } else if (e.getSource() == this.btValider) {
            // On récupère les champs
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String mdp1 = new String(this.txtMdp1.getPassword());
            String mdp2 = new String(this.txtMdp2.getPassword());
            String email = this.txtEmail.getText();

            ArrayList<String> leschamps = new ArrayList<>();
            leschamps.add(nom);
            leschamps.add(prenom);
            leschamps.add(mdp1);
            leschamps.add(mdp2);
            leschamps.add(email);

            // On vérifie si les champs sont remplis et on vérifie l'égalité des mots de passe
            if (Controleur.verifDonnees(leschamps) && mdp1.equals(mdp2)) {
                // On instancie le unSalarie
                unSalarie.setNom(nom);
                unSalarie.setPrenom(prenom);
                unSalarie.setEmail(email);

                // On modifie dans la base de données
                Controleur.updateSalarie(unSalarie);

                // On modifie le technicien connecté
                Orange.setTechConnecte(unSalarie);

                // Message de confirmation
                JOptionPane.showMessageDialog(this, "Modification réussie du technicien.",
                        "Modification du technicien", JOptionPane.INFORMATION_MESSAGE);
                this.textInfos.setText("_________________INFO PROFIL_______________\n\n"
                        + "Nom Utilisateur : " + unSalarie.getNom() + "\n\n"
                        + "Prénom Utilisateur : " + unSalarie.getPrenom() + "\n\n"
                        + "Email Utilisateur : " + unSalarie.getEmail() + "\n"
                        + "__________________________________________________________");
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.",
                        "Modification du technicien", JOptionPane.ERROR_MESSAGE);
            }
            // On vide les données
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtEmail.setText("");
            this.txtMdp1.setText("");
            this.txtMdp2.setText("");

            // On rend le formulaire invisible
            this.panelForm.setVisible(false);
        }
    }
}
