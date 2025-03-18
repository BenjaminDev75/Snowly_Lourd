package controleur;

public class Salarie {
    private int idSalarie,ID_Utilisateur;
    private String nom, prenom,email;

    public Salarie(int ID_Utilisateur, int idSalarie, String nom, String prenom, String email) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.idSalarie = idSalarie;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;

    }
    public Salarie( int ID_Utilisateur, String nom, String prenom, String email) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.idSalarie = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;

    }

    public int getIdSalarie() {
        return idSalarie;
    }

    public void setIdSalarie(int idSalarie) {
        this.idSalarie = idSalarie;
    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
