package vue;

import javax.swing.*;
import java.awt.*;

public abstract class PanelPrincipal extends JPanel {

    private JLabel lbTitre = new JLabel();

    public PanelPrincipal(String titre) {
        this.setBackground(new Color(60, 63, 65)); // Couleur de fond moderne
        this.setBounds(50, 80, 1380, 750);
        this.lbTitre = new JLabel(titre);
        this.lbTitre.setBounds(600, 20, 400, 30);
        Font unePolice = new Font("Arial", Font.BOLD, 24); // Police plus grande et moderne
        this.lbTitre.setFont(unePolice);
        this.lbTitre.setForeground(Color.WHITE); // Couleur de texte blanche pour contraste
        this.add(this.lbTitre);

        this.setLayout(null);

        this.setVisible(false);
    }
}
