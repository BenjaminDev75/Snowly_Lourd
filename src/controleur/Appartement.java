package controleur;

public class Appartement {
    private int ID_Appartement ,Capacite_Accueil, ID_Station, ID_Utilisateur, idImage;
    private String Nom_Immeuble, Adresse, CP, Ville, Exposition, Description;
    private float Surface_Habitable, Surface_Balcon, Distance_Pistes, Tarif ;


    public Appartement(int ID_Appartement, String Nom_Immeuble, String Adresse, String CP,  String Ville,  String Exposition,   float Surface_Habitable, float Surface_Balcon,
                       int Capacite_Accueil, float Distance_Pistes, String Description, float Tarif,int ID_Station, int ID_Utilisateur, int idImage ) {

        this.ID_Appartement = ID_Appartement;
        this.Nom_Immeuble = Nom_Immeuble;
        this.Adresse = Adresse;
        this.CP = CP;
        this.Ville = Ville;
        this.Exposition = Exposition;
        this.Surface_Habitable = Surface_Habitable;
        this.Surface_Balcon = Surface_Balcon;
        this.Capacite_Accueil = Capacite_Accueil;
        this.Distance_Pistes = Distance_Pistes;
        this.Description = Description;
        this.Tarif = Tarif;
        this.ID_Station = ID_Station;
        this.ID_Utilisateur = ID_Utilisateur;
        this.idImage = idImage;
    }

    public Appartement( String Nom_Immeuble, String Adresse, String CP,  String Ville,  String Exposition,   float Surface_Habitable, float Surface_Balcon,
                       int Capacite_Accueil, float Distance_Pistes, String Description, float Tarif) {

        this.ID_Appartement = 0;
        this.Nom_Immeuble = Nom_Immeuble;
        this.Adresse = Adresse;
        this.CP = CP;
        this.Ville = Ville;
        this.Exposition = Exposition;
        this.Surface_Habitable = Surface_Habitable;
        this.Surface_Balcon = Surface_Balcon;
        this.Capacite_Accueil = Capacite_Accueil;
        this.Distance_Pistes = Distance_Pistes;
        this.Description = Description;
        this.Tarif = Tarif;
        this.ID_Station = 0;
        this.ID_Utilisateur = 0;
        this.idImage = 0;
    }

    public int getID_Appartement() {
        return ID_Appartement;
    }

    public void setID_Appartement(int ID_Appartement) {
        this.ID_Appartement = ID_Appartement;
    }

    public int getCapacite_Accueil() {
        return Capacite_Accueil;
    }

    public void setCapacite_Accueil(int capacite_Accueil) {
        Capacite_Accueil = capacite_Accueil;
    }

    public int getID_Station() {
        return ID_Station;
    }

    public void setID_Station(int ID_Station) {
        this.ID_Station = ID_Station;
    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getNom_Immeuble() {
        return Nom_Immeuble;
    }

    public void setNom_Immeuble(String nom_Immeuble) {
        Nom_Immeuble = nom_Immeuble;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getExposition() {
        return Exposition;
    }

    public void setExposition(String exposition) {
        Exposition = exposition;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getSurface_Habitable() {
        return Surface_Habitable;
    }

    public void setSurface_Habitable(float surface_Habitable) {
        Surface_Habitable = surface_Habitable;
    }

    public float getSurface_Balcon() {
        return Surface_Balcon;
    }

    public void setSurface_Balcon(float surface_Balcon) {
        Surface_Balcon = surface_Balcon;
    }

    public float getDistance_Pistes() {
        return Distance_Pistes;
    }

    public void setDistance_Pistes(float distance_Pistes) {
        Distance_Pistes = distance_Pistes;
    }

    public float getTarif() {
        return Tarif;
    }

    public void setTarif(float tarif) {
        Tarif = tarif;
    }
}
