package controleur;

public class Utilisateur {
    private int ID_Utilisateur, idClient,idProprietaire;
    private String nom,Date_Creation;

    public Utilisateur(int ID_Utilisateur, int idClient, int idProprietaire, String nom, String Date_Creation, float prixInter) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.idClient = idClient;
        this.idProprietaire = idProprietaire;
        this.nom = nom;
        this.Date_Creation = Date_Creation;
    }

    public Utilisateur(int idClient, int idProprietaire, String nom, String Date_Creation, float prixInter) {
        this.ID_Utilisateur = 0;
        this.idClient = idClient;
        this.idProprietaire = idProprietaire;
        this.nom = nom;
        this.Date_Creation = Date_Creation;
    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(int idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate_Creation() {
        return Date_Creation;
    }

    public void setDate_Creation(String date_Creation) {
        Date_Creation = date_Creation;
    }
}
