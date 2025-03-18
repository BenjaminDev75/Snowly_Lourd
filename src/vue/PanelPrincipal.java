package vue;

import javax.swing.*;
import java.awt.*;

public abstract class PanelPrincipal extends JPanel {

    private JLabel lbTitre = new JLabel();

    public PanelPrincipal(String titre) {
        this.setBackground(Color.cyan);
        this.setBounds(50,80,1380,750);
        this.lbTitre = new JLabel(titre);
        this.lbTitre.setBounds(600,20,300,30);
        Font unePolice = new Font("Arial", Font.BOLD, 20);
        this.lbTitre.setFont(unePolice);
        this.add(this.lbTitre);

        this.setLayout(null);

        this.setVisible(false);

    }
}
