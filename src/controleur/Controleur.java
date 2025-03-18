package controleur;

import modele.Modele;

import java.util.ArrayList;

public class Controleur {


    /* CONTROLE DES DONNEES */

    public static boolean verifDonnees(ArrayList<String> lesChamps) {
        boolean ok = true;

        for (String champ : lesChamps) {
            if(champ.isEmpty()){
                ok = false;
            }
        }
        return ok;
    }
    /**********************************************************************************/

    public static int count(String table){
        return Modele.count(table);
    }



    /* GESTION SALARIE*/

    public static void insertSalarie(Salarie unSalarie){
        // coté securité : on verifie les données avant insertion dans la bdd

        // appel du modele pour inserer le client
        Modele.insertSalarie(unSalarie);
    }

    public static ArrayList<Salarie> selectAllSalaries(){
        return Modele.selectAllSalaries();
    }

    public static  void deleteSalarie(int idSalarie){
        Modele.deleteClient(idSalarie);
    }

    public static void updateSalarie(Salarie unSalarie){
        Modele.updateSalarie(unSalarie);
    }
    public static ArrayList<Salarie> selectLikeSalaries(String filtre){
        return Modele.selectLikeSalarie(filtre);
    }
    public static Salarie selectWhereSalarie(int idSalarie){
        return Modele.selectWhereSalarie(idSalarie);
    }
    public static Salarie selectWhereSalaries(String email, String mdp) {
        return Modele.selectWhereSalaries(email, mdp);
    }








/* GESTION DES CLIENTS*/



    public static void insertClient(Client unClient){
        // coté securité : on verifie les données avant insertion dans la bdd

        // appel du modele pour inserer le client
        Modele.insertClient(unClient);
    }

    public static ArrayList<Client> selectAllClients(){
        return Modele.selectAllClients();
    }

    public static  void deleteClient(int idclient){
        Modele.deleteClient(idclient);
    }

    public static void updateClient(Client unClient){
        Modele.updateClient(unClient);
    }
    public static ArrayList<Client> selectLikeClients(String filtre){
        return Modele.selectLikeClient(filtre);
    }
    public static Client selectWhereCLient(int idclient){
        return Modele.selectWhereClient(idclient);
    }



    /* GESTION DES APPARTEMENTS*/
    public static void insertAppartement(Appartement unAppartement){
        // coté securité : on verifie les données avant insertion dans la bdd

        // appel du modele pour inserer le appartement
        Modele.insertAppartement(unAppartement);
    }

    public static ArrayList<Appartement> selectAllAppartements(){
        return Modele.selectAllAppartements();
    }

    public static  void deleteAppartement(int ID_Appartement){
        Modele.deleteAppartement(ID_Appartement);
    }

    public static void updateAppartement(Appartement unAppartement){
        Modele.updateAppartement(unAppartement);
    }
    public static ArrayList<Appartement> selectLikeAppartements(String filtre){
        return Modele.selectLikeAppartement(filtre);
    }
    public static Appartement selectWhereAppartement(int ID_Appartement){
        return Modele.selectWhereAppartement(ID_Appartement);
    }













    public static void insertProprietaire(Proprietaire unTechnicien){
        // coté securité : on verifie les données avant insertion dans la bdd

        // appel du modele pour inserer le client
        Modele.insertProprietaire(unTechnicien);
    }

    public static ArrayList<Proprietaire> selectAllProprietaire(){
        return Modele.selectAllProprietaire();
    }

    public static  void deleteTechnicien(int idtechnicien){
        Modele.deleteTechnicien(idtechnicien);
    }

    public static void updateProprietaire(Proprietaire unProprietaire){
        Modele.updateProprietaire(unProprietaire);
    }
    public static ArrayList<Proprietaire> selectLikeProprietaire(String filtre){
        return Modele.selectLikeProprietaire(filtre);
    }
    public static Proprietaire selectWhereProprietaire(int idProprietaire){
        return Modele.selectWhereProprietaire(idProprietaire);
    }

    public static Proprietaire selectWhereProprietaires(String email, String mdp) {
        return Modele.selectWhereProprietaires(email,mdp);
    }

    /* GESTION RESERVATION */

    public static ArrayList<Reservation> selectAllReservations(){
        return Modele.selectAllReservations();
    }

    public static  void deleteReservation(int idReservation){
        Modele.deleteReservation(idReservation);
    }

}
