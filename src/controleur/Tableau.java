package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel {

    private Object donnees[][];
    private String entetes[];

    public Tableau(Object[][] donnees, String[] entetes) {
        this.donnees = donnees;
        this.entetes = entetes;
    }

    @Override
    public int getRowCount() {
        return this.donnees.length; // nb ligne
    }

    @Override
    public int getColumnCount() {
        return this.entetes.length; // nb colonnes
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.donnees[rowIndex][columnIndex]; // return la valeur de l'element [ligne][colonne]
    }

    @Override
    public String getColumnName(int column) {
        return this.entetes[column]; // retourn le nom de la colonne
    }

    public void setDonnees(Object donnees[][]){
        this.donnees = donnees;
        this.fireTableDataChanged(); // actualiser les données
    }
}
