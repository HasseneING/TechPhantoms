package model;

import java.sql.Date;

public class Reservation {

    int id;
    Date reservation_date;
    int tutor;
    int student;
    String user_student;
    String user_tutor;

    public Reservation(int id, Date reservation_date, int tutor, int student, String user_student, String user_tutor) {
        this.id = id;
        this.reservation_date = reservation_date;
        this.tutor = tutor;
        this.student = student;
        this.user_student = user_student;
        this.user_tutor = user_tutor;
    }

    public Reservation(int id, Date reservation_date,String user_student) {
        this.id = id;
        this.reservation_date = reservation_date;
        this.user_student = user_student;
    }

    public Reservation(int id, Date reservation_date,String user_student,String user_tutor) {
        this.id = id;
        this.reservation_date = reservation_date;
        this.user_student = user_student;
        this.user_tutor=user_tutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Date reservation_date) {
        this.reservation_date = reservation_date;
    }

    public int getTutor() {
        return tutor;
    }

    public void setTutor(int tutor) {
        this.tutor = tutor;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public String getUser_student() {
        return user_student;
    }

    public void setUser_student(String user_student) {
        this.user_student = user_student;
    }

    public String getUser_tutor() {
        return user_tutor;
    }

    public void setUser_tutor(String user_tutor) {
        this.user_tutor = user_tutor;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservation_date=" + reservation_date +
                ", tutor=" + tutor +
                ", student=" + student +
                ", user_student='" + user_student + '\'' +
                ", user_tutor='" + user_tutor + '\'' +
                '}';
    }
}
