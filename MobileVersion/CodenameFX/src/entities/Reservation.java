package entities;

public class Reservation {

    int reservationId;
    String reservationDate;
    String tutor_id;
    String student_id;

    public Reservation(int reservationId, String reservationDate, String tutor_id, String student_id) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.tutor_id = tutor_id;
        this.student_id = student_id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", reservationDate='" + reservationDate + '\'' +
                ", tutor_id='" + tutor_id + '\'' +
                ", student_id='" + student_id + '\'' +
                '}';
    }
    public Reservation() {
    }
}
