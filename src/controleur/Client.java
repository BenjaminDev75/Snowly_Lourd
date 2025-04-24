package controleur;

public class Client {
    private int idClient,ID_Utilisateur;
    private String nom, prenom, adresse, ville, cp, email, Telephone;

    public Client(int ID_Utilisateur, int idClient, String nom, String prenom, String adresse, String email, String ville, String cp, String Telephone) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.Telephone = Telephone;
    }
    public Client( String nom, String prenom, String email, String adresse,  String ville, String cp, String Telephone) {
        this.ID_Utilisateur = 0;
        this.idClient = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.Telephone = Telephone;
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
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
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTel(String Telephone) {
        this.Telephone = Telephone;
    }


}
