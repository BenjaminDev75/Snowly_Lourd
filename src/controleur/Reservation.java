package controleur;

public class Reservation {
    private int ID_Reservation,ID_Utilisateur,ID_Appartement;
    private float Montant_Total;
    private String DateReservation, DateDebut, DateFin;



    public Reservation(int ID_Reservation, String DateReservation, String DateDebut, String DateFin, float Montant_Total, int ID_Utilisateur,int ID_Appartement) {
        this.ID_Reservation = ID_Reservation;
        this.DateReservation = DateReservation;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Montant_Total = Montant_Total;
        this.ID_Utilisateur = ID_Utilisateur;
        this.ID_Appartement = ID_Appartement;
    }
    public Reservation(String DateReservation, String DateDebut, String DateFin, float Montant_Total, int ID_Utilisateur,int ID_Appartement) {
        this.ID_Reservation = 0;
        this.DateReservation = DateReservation;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Montant_Total = Montant_Total;
        this.ID_Utilisateur = ID_Utilisateur;
        this.ID_Appartement = ID_Appartement;
    }

    public int getID_Reservation() {
        return ID_Reservation;
    }

    public void setID_Reservation(int ID_Reservation) {
        this.ID_Reservation = ID_Reservation;
    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public int getID_Appartement() {
        return ID_Appartement;
    }

    public void setID_Appartement(int ID_Appartement) {
        this.ID_Appartement = ID_Appartement;
    }

    public float getMontant_Total() {
        return Montant_Total;
    }

    public void setMontant_Total(float montant_Total) {
        Montant_Total = montant_Total;
    }

    public String getDateReservation() {
        return DateReservation;
    }

    public void setDateReservation(String dateReservation) {
        DateReservation = dateReservation;
    }

    public String getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(String dateDebut) {
        DateDebut = dateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setDateFin(String dateFin) {
        DateFin = dateFin;
    }
}
