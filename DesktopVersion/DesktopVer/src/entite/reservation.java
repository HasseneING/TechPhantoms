package entite;

import java.sql.Timestamp;

public class reservation {
    private int reservationID;
    private int  dateID;
    private int teacherID;
    private Timestamp reservationDATE;
    private String expired;


    public reservation(int reservationID, int dateID, int teacherID, Timestamp reservationDATE,  String expired) {
        this.reservationID = reservationID;
        this.dateID = dateID;
        this.teacherID = teacherID;
        this.reservationDATE=reservationDATE;
        this.expired = expired;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getDateID() {
        return dateID;
    }

    public void setDateID(int dateID) {
        this.dateID = dateID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public Timestamp getReservationDATE() {
        return reservationDATE;
    }

    public void setReservationDATE(Timestamp reservationDATE) {
        this.reservationDATE = reservationDATE;
    }

    @Override
    public String toString() {
        return "reservation{" +
                "reservationID=" + reservationID +
                ", dateID=" + dateID +
                ", teacherID=" + teacherID +
                ", reservationDATE=" + reservationDATE +
                ", expired='" + expired + '\'' +
                '}';
    }
}
