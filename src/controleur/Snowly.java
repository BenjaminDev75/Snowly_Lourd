package controleur;


import vue.VueConnexion;
import vue.VueGenerale;

public class Snowly {

    // instanciation de la classe VueConnexion
    private static VueConnexion uneVueConnexion;

    private static VueGenerale uneVueGenerale;

    private static Salarie techConnecte;

    public static void setTechConnecte(Salarie unSalarie) {
        techConnecte = unSalarie;
    }
    public static Salarie getTechConnecte() {
        return techConnecte;
    }

    public static void creerVueGenerale(boolean action){
        if(action == true){
            uneVueGenerale = new VueGenerale();
            uneVueGenerale.setVisible(true);
        }else{
            uneVueGenerale.dispose();
        }
    }

    public static void rendreVisibleVueConnexion(boolean action){
        uneVueConnexion.setVisible(action);
    }


    public static void main(String[] args) {

        uneVueConnexion = new VueConnexion();


    }
}
