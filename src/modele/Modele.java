package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.*;

public class Modele {
    private static Connexion uneConnexion = new Connexion ("localhost", "ns", "root", "");





    /************************ GESTION DES SALARIE **********************/
    public static void insertSalarie(Salarie unSalarie) {
        String requete = "insert into salarie values (null, '"+unSalarie.getNom()
                + "','" + unSalarie.getPrenom() + "','" + unSalarie.getEmail() +"');";

        executerRequete (requete);
    }

    public static ArrayList<Salarie> selectAllSalaries (){
        ArrayList<Salarie> lesSalaries = new ArrayList<Salarie>();
        String requete ="select * from salarie;";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while(lesResultats.next()) {
                //instanciation d'un Salarie
                Salarie unSalarie = new Salarie(
                        lesResultats.getInt("ID_Utilisateur"), lesResultats.getInt("idSalarie"),
                        lesResultats.getString("nom"),lesResultats.getString("prenom"),
                        lesResultats.getString("email")
                );
                //on ajoute le Salarie dans l'ArrayList
                lesSalaries.add(unSalarie);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesSalaries;
    }

    public static void deleteSalarie (int idSalarie) {
        String requete = "delete from salarie where idSalarie = "+idSalarie+";";
        executerRequete(requete);
    }

    public static void updateSalarie(Salarie unSalarie) {
        String requete ="update salarie set nom = '" + unSalarie.getNom()
                + "', prenom ='"+unSalarie.getPrenom()
                + "', email ='"+unSalarie.getEmail()
                + "'  where  idSalarie = "+unSalarie.getIdSalarie()+";";

        executerRequete(requete);
    }

    public static ArrayList<Salarie> selectLikeSalarie (String filtre){
        ArrayList<Salarie> lesSalaries = new ArrayList<Salarie>();
        String requete ="select * from salarie where nom like '%"+filtre
                +"%' or prenom like '%" + filtre + "%' or email like '%" + filtre + "%' ; ";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while(lesResultats.next()) {
                //instanciation d'un Salarie
                Salarie unSalarie = new Salarie(
                        lesResultats.getInt("ID_Utilisateur"), lesResultats.getInt("idSalarie"),
                        lesResultats.getString("nom"), lesResultats.getString("prenom"),
                        lesResultats.getString("email")
                );
                //on ajoute le Salarie dans l'ArrayList
                lesSalaries.add(unSalarie);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesSalaries;
    }

    public static Salarie selectWhereSalarie(int idSalarie) {
        String requete ="select * from salarie where idSalarie = "+idSalarie+";";
        Salarie unSalarie = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if(unResultat.next()) {
                //instanciation du Salarie
                unSalarie = new Salarie(
                        unResultat.getInt("ID_Utilisateur"), unResultat.getInt("idSalarie"),
                        unResultat.getString("nom"), unResultat.getString("prenom"),
                        unResultat.getString("email")
                );
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unSalarie;
    }



    public static Salarie selectWhereSalaries(String email, String mdp) {
        String requete ="select * from Salarie  " +
                "where email = '"+email+"' and mdp = '" + mdp + "';" ;

        Salarie unSalarie = null;

        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if(unResultat.next()) {
                //instanciation du Salarie
                unSalarie = new Salarie(
                        unResultat.getInt("ID_Utilisateur"),unResultat.getInt("idSalarie"), unResultat.getString("nom"),
                        unResultat.getString("prenom"),unResultat.getString("email")
                );
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unSalarie;
    }












    /************************ GESTION DES CLIENTS **********************/

    public static void insertClient(Client unClient) {
 // Génération automatique d'un mot de passe (par exemple, une chaîne aléatoire de 8 caractères)
 String motDePasse = generateRandomPassword(8);

 String requete = "INSERT INTO client (ID_Utilisateur, idClient, prenom, nom, email, Mot_De_Passe, Adresse, Ville, CP, Telephone) " +
 "VALUES (null, null, '" + unClient.getPrenom() + "', '" + unClient.getNom() + "', '" + unClient.getEmail() + "', '" + motDePasse + "', '" + unClient.getAdresse()  + "', '" + unClient.getVille() + "', '" + unClient.getCp() + "', '" +  unClient.getTelephone() + "');";

 executerRequete(requete);
    }


    // Méthode pour générer un mot de passe aléatoire
    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }


    public static ArrayList<Client> selectAllClients (){
        ArrayList<Client> lesClients = new ArrayList<Client>();
        String requete ="select * from client;";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while(lesResultats.next()) {
                //instanciation d'un client
                Client unClient = new Client(
                        lesResultats.getInt("ID_Utilisateur"), lesResultats.getInt("idClient"),
                        lesResultats.getString("prenom"), lesResultats.getString("nom"),
                        lesResultats.getString("email"), lesResultats.getString("Adresse"),
                        lesResultats.getString("Ville"), lesResultats.getString("CP"),
                        lesResultats.getString("Telephone")
                );
                //on ajoute le client dans l'ArrayList
                lesClients.add(unClient);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesClients;
    }

    public static void deleteClient (int idclient) {
        String requete = "delete from client where idclient = "+idclient+";";
        executerRequete(requete);
    }

    public static void updateClient(Client unClient) {
        String requete ="update client set nom = '" + unClient.getNom()
                + "', prenom ='"+unClient.getPrenom() + "', adresse='" + unClient.getAdresse()
                + "', email ='"+unClient.getEmail() + "', Telephone='" + unClient.getTelephone()
                + "'  where  idclient = "+unClient.getIdClient()+";";

        executerRequete(requete);
    }

    public static ArrayList<Client> selectLikeClient (String filtre){
        ArrayList<Client> lesClients = new ArrayList<Client>();
        String requete ="select * from client where nom like '%"+filtre
                +"%' or prenom like '%" + filtre + "%' or adresse like '%"
                + filtre + "%' or email like '%" + filtre + "%' or tel like '%"
                + filtre + "%' ; ";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while(lesResultats.next()) {
                //instanciation d'un client
                Client unClient = new Client(
                        lesResultats.getInt("ID_Utilisateur"), lesResultats.getInt("idClient"),
                        lesResultats.getString("prenom"), lesResultats.getString("nom"),
                        lesResultats.getString("email"), lesResultats.getString("Adresse"),
                        lesResultats.getString("Ville"), lesResultats.getString("CP"),
                        lesResultats.getString("Telephone")
                );
                //on ajoute le client dans l'ArrayList
                lesClients.add(unClient);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesClients;
    }

    public static Client selectWhereClient(int idclient) {
        String requete ="select * from client where idclient = "+idclient+";";
        Client unClient = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if(unResultat.next()) {
                //instanciation du client
                unClient = new Client(
                        unResultat.getInt("ID_Utilisateur"), unResultat.getInt("idClient"),
                        unResultat.getString("prenom"), unResultat.getString("nom"),
                        unResultat.getString("email"), unResultat.getString("Adresse"),
                        unResultat.getString("Ville"), unResultat.getString("CP"),
                        unResultat.getString("Telephone")
                );
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unClient;
    }




    /************************ GESTION DES APPARTEMENT **********************/
    public static void insertAppartement(Appartement unAppartement) {
        String requete = "insert into appartement values (null, '"+unAppartement.getID_Appartement()
                + "','" +unAppartement.getNom_Immeuble() + "','" + unAppartement.getAdresse()
                +"','" + unAppartement.getCP() + "','" + unAppartement.getVille() + "','" + unAppartement.getExposition()
                +"','" + unAppartement.getSurface_Habitable() + "','" + unAppartement.getSurface_Balcon()
                +"','" + unAppartement.getCapacite_Accueil() + "','" + unAppartement.getDistance_Pistes()
                +"','" + unAppartement.getDescription() + "','" + unAppartement.getTarif() + "','" + unAppartement.getID_Station()
                + "','" + unAppartement.getID_Utilisateur()
                +"');";

        executerRequete (requete);
    }

    public static ArrayList<Appartement> selectAllAppartements (){
        ArrayList<Appartement> lesAppartements = new ArrayList<Appartement>();
        String requete ="select * from appartement ;";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while(lesResultats.next()) {
                //instanciation d'un client
                Appartement unAppartement = new Appartement(
                        lesResultats.getInt("ID_Appartement"), lesResultats.getString("Nom_Immeuble"),
                        lesResultats.getString("Adresse"), lesResultats.getString("CP"),
                        lesResultats.getString("Ville"), lesResultats.getString("Exposition"),
                        lesResultats.getFloat("Surface_Habitable"), lesResultats.getFloat("Surface_Balcon"),
                        lesResultats.getInt("Capacite_Accueil"), lesResultats.getFloat("Distance_Pistes"),
                        lesResultats.getString("Description"),lesResultats.getFloat("Tarif"),
                        lesResultats.getInt("ID_Station"), lesResultats.getInt("ID_Utilisateur"),
                        lesResultats.getInt("idImage")
                );
                //on ajoute le client dans l'ArrayList
                lesAppartements.add(unAppartement);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
            exp.printStackTrace();  // Afficher le détail de l'erreur SQL

        }
        return lesAppartements;
    }



    public static void deleteAppartement (int ID_Appartement) {
        String requete = "delete from appartement where ID_Appartement = "+ID_Appartement+";";
        executerRequete(requete);
    }

    public static void updateAppartement(Appartement unAppartement) {
        String requete ="update appartement set Nom_Immeuble = '" + unAppartement.getNom_Immeuble()
                + "', Adresse ='"+unAppartement.getAdresse() + "', CP ='" + unAppartement.getCP()
                + "', Ville ='" + unAppartement.getVille() + "', Exposition ='" + unAppartement.getExposition()
                + "', Exposition ='"+ unAppartement.getExposition() + "', Surface_Habitable ='" + unAppartement.getSurface_Habitable()
                + "', Surface_Balcon ='" + unAppartement.getSurface_Balcon() + "', Capacite_Accueil ='" + unAppartement.getCapacite_Accueil()
                + "', Distance_Pistes ='"+ unAppartement.getDistance_Pistes() + "', Description ='" + unAppartement.getDescription()
                +"', Tarif ='"+ unAppartement.getTarif() + "', ID_Station ='"
                + "'  where  ID_Appartement = "+unAppartement.getID_Appartement()+";";

        executerRequete(requete);
    }

    public static ArrayList<Appartement> selectLikeAppartement (String filtre){
        ArrayList<Appartement> lesAppartements = new ArrayList<Appartement>();
        String requete ="select * from appartement where Nom_Immeuble like '%"+filtre
                +"%' or Adresse like '%" + filtre + "%' or CP like '%"
                + filtre + "%' or Ville like '%" + filtre + "%' ; ";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while(lesResultats.next()) {
                //instanciation d'un client
                Appartement unAppartement = new Appartement(
                        lesResultats.getInt("ID_Appartement"), lesResultats.getString("Nom_Immeuble"),
                        lesResultats.getString("Adresse"), lesResultats.getString("CP"),
                        lesResultats.getString("Ville"), lesResultats.getString("Exposition"),
                        lesResultats.getFloat("Surface_Habitable"), lesResultats.getFloat("Surface_Balcon"),
                        lesResultats.getInt("Capacite_Accueil"), lesResultats.getFloat("Distance_Pistes"),
                        lesResultats.getString("Description"),lesResultats.getFloat("Tarif"),
                        lesResultats.getInt("ID_Station"), lesResultats.getInt("ID_Utilisateur"),
                        lesResultats.getInt("idImage")
                );
                //on ajoute le client dans l'ArrayList
                lesAppartements.add(unAppartement);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesAppartements;
    }

    public static Appartement selectWhereAppartement(int ID_Appartement) {
        String requete ="select * from client where idclient = "+ID_Appartement+";";
        Appartement unAppartement = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if(unResultat.next()) {
                //instanciation du client
                unAppartement = new Appartement(
                        unResultat.getInt("ID_Appartement"), unResultat.getString("Nom_Immeuble"),
                        unResultat.getString("Adresse"), unResultat.getString("CP"),
                        unResultat.getString("Ville"), unResultat.getString("Exposition"),
                        unResultat.getFloat("Surface_Habitable"), unResultat.getFloat("Surface_Balcon"),
                        unResultat.getInt("Capacite_Accueil"), unResultat.getFloat("Distance_Pistes"),
                        unResultat.getString("Description"),unResultat.getFloat("Tarif"),
                        unResultat.getInt("ID_Station"), unResultat.getInt("ID_Utilisateur"),
                        unResultat.getInt("idImage")
                );
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unAppartement;
    }





























    /************************ GESTION DES PROPRIETAIRE **********************/
    public static void insertProprietaire(Proprietaire unProprietaire) {
        String requete = "insert into technicien values ('"+unProprietaire.getID_Utilisateur()+"','"+"'null, '"+unProprietaire.getNom()
                + "','" + unProprietaire.getPrenom() + "','" + unProprietaire.getAdresse() +"');";

        executerRequete (requete);
    }

    public static ArrayList<Proprietaire> selectAllProprietaire (){
        ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>();
        String requete ="select * from proprietaire;";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while(lesResultats.next()) {
                //instanciation d'un client
                Proprietaire unTechnicien = new Proprietaire(
                        lesResultats.getInt("ID_Utilisateur"),lesResultats.getInt("idProprietaire"), lesResultats.getString("nom"),
                        lesResultats.getString("prenom"),lesResultats.getString("Adresse")
                );
                //on ajoute le client dans l'ArrayList
                lesProprietaires.add(unTechnicien);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesProprietaires;
    }

    public static void deleteTechnicien (int idtechnicien) {
        String requete = "delete from technicien where idtechnicien = "+idtechnicien+";";
        executerRequete(requete);
    }

    public static void updateProprietaire(Proprietaire unProprietaire) {
        String requete ="update technicien set nom = '" + unProprietaire.getNom()
                + "', prenom ='"+unProprietaire.getPrenom() + "', qualification='" + unProprietaire.getAdresse()
                + "'  where  idtechnicien = "+unProprietaire.getIdProprietaire()+";";

        executerRequete(requete);
    }

    public static ArrayList<Proprietaire> selectLikeProprietaire (String filtre){
        ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>();
        String requete ="select * from proprietaire where nom like '%"+filtre
                +"%' or prenom like '%" + filtre + "%' or Adresse like '%"
                + filtre + "%' ; ";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while(lesResultats.next()) {
                //instanciation d'un client
                Proprietaire unTechnicien = new Proprietaire(
                        lesResultats.getInt("ID_Utilisateur"),lesResultats.getInt("idProprietaire"), lesResultats.getString("nom"),
                        lesResultats.getString("prenom"),lesResultats.getString("Adresse")
                );
                //on ajoute le client dans l'ArrayList
                lesProprietaires.add(unTechnicien);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesProprietaires;
    }

    public static Proprietaire selectWhereProprietaire(int idProprietaire) {
        String requete ="select * from proprietaire where idProprietaire = "+idProprietaire+";";
        Proprietaire unProprietaire = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if(unResultat.next()) {
                //instanciation du client
                unProprietaire = new Proprietaire(
                        unResultat.getInt("ID_Utilisateur"),unResultat.getInt("idProprietaire"), unResultat.getString("nom"),
                        unResultat.getString("prenom"),unResultat.getString("Adresse")
                );
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unProprietaire;
    }


    public static Proprietaire selectWhereProprietaires(String email, String mdp) {
        String requete ="select * from Proprietaire p " +
                "join client c on c.ID_Utilisateur = p.ID_Utilisateur "+
                "where c.email = '"+email+"' and c.Mot_De_Passe = '" + mdp + "';" ;

        Proprietaire unProprietaire = null;

        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if(unResultat.next()) {
                //instanciation du client
                unProprietaire = new Proprietaire(
                        unResultat.getInt("ID_Utilisateur"),unResultat.getInt("idProprietaire"), unResultat.getString("nom"),
                        unResultat.getString("prenom"),unResultat.getString("Adresse")
                );
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unProprietaire;
    }

    /* GESTION RESERVATION*/

    public static ArrayList<Reservation> selectAllReservations() {
        ArrayList<Reservation> lesReservations = new ArrayList<>();
        String requete = "SELECT * FROM reservation;";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()) {
                // Instanciation d'une réservation
                Reservation uneReservation = new Reservation(
                        lesResultats.getInt("ID_Reservation"),
                        lesResultats.getString("DateReservation"),
                        lesResultats.getString("DateDebut"),
                        lesResultats.getString("DateFin"),
                        lesResultats.getFloat("Montant_Total"),
                        lesResultats.getInt("ID_Utilisateur"),
                        lesResultats.getInt("ID_Appartement")
                );
                // Ajout de la réservation à la liste
                lesReservations.add(uneReservation);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return lesReservations;
    }

    public static void deleteReservation(int idReservation) {
        String requete = "DELETE FROM reservation WHERE ID_Reservation = " + idReservation + ";";
        executerRequete(requete);
    }


    /************************** Autres méthodes *******************/
    public static void executerRequete (String requete) {
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static int count(String table){
        int nb =0;

        String requete = "select count(*) as nb from "+table+";";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if(unResultat.next()) {
                nb = unResultat.getInt("nb");
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        }
        catch(SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }

        return nb;
    }


    public static void insertReservation(Reservation uneReservation) {
        String requete = "INSERT INTO reservation (ID_Reservation, DateReservation, DateDebut, DateFin, Montant_Total, ID_Utilisateur, ID_Appartement) VALUES ("
                + uneReservation.getID_Reservation() + ", '"
                + uneReservation.getDateReservation() + "', '"
                + uneReservation.getDateDebut() + "', '"
                + uneReservation.getDateFin() + "', "
                + uneReservation.getMontant_Total() + ", "
                + uneReservation.getID_Utilisateur() + ", "
                + uneReservation.getID_Appartement() + ");";

        executerRequete(requete);
    }


}