package entities;

public class Meet {
int meet_id;
    String meet_name;
    String meet_pass;
    String meet_link;
    String tutor_id;
    String student_id;
    String meet_date;

    public Meet(int meet_id, String meet_name, String meet_pass, String meet_link, String tutor_id, String student_id, String meet_date) {
        this.meet_id = meet_id;
        this.meet_name = meet_name;
        this.meet_pass = meet_pass;
        this.meet_link = meet_link;
        this.tutor_id = tutor_id;
        this.student_id = student_id;
        this.meet_date = meet_date;
    }

    public Meet(String meet_name, String meet_pass) {
        this.meet_name = meet_name;
        this.meet_pass = meet_pass;
    }
    public Meet() {
    }

    public int getMeet_id() {
        return meet_id;
    }

    public void setMeet_id(int meet_id) {
        this.meet_id = meet_id;
    }

    public String getMeet_name() {
        return meet_name;
    }

    public void setMeet_name(String meet_name) {
        this.meet_name = meet_name;
    }

    public String getMeet_pass() {
        return meet_pass;
    }

    public void setMeet_pass(String meet_pass) {
        this.meet_pass = meet_pass;
    }

    public String getMeet_link() {
        return meet_link;
    }

    public void setMeet_link(String meet_link) {
        this.meet_link = meet_link;
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

    public String getMeet_date() {
        return meet_date;
    }

    public void setMeet_date(String meet_date) {
        this.meet_date = meet_date;
    }

    @Override
    public String toString() {
        return "Meet{" +
                "meet_id=" + meet_id +
                ", meet_name='" + meet_name + '\'' +
                ", meet_pass='" + meet_pass + '\'' +
                ", meet_link='" + meet_link + '\'' +
                ", tutor_id=" + tutor_id +
                ", student_id=" + student_id +
                ", meet_date=" + meet_date +
                '}';
    }
}
