package controleur;

public class Proprietaire {
    private int ID_Utilisateur, idProprietaire;
    private String nom, prenom, Adresse;

    public Proprietaire(int ID_Utilisateur, int idProprietaire, String nom, String prenom, String Adresse) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.idProprietaire =idProprietaire;
        this.nom = nom;
        this.prenom = prenom;
        this.Adresse = Adresse;
    }

    public Proprietaire(int ID_Utilisateur, String nom, String prenom, String Adresse) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.idProprietaire =0;
        this.nom = nom;
        this.prenom = prenom;
        this.Adresse = Adresse;
    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }
}
