package entite;


public class Meet {
    private int room_id;
    private String room_name;
    private int TeacherID;


    public Meet() {
    }

    public Meet(int room_id, int teacherID, String room_name) {
        this.room_id = room_id;
        this.TeacherID = teacherID;
        this.room_name = room_name;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int teacherID) {
        TeacherID = teacherID;
    }

    @Override
    public String toString() {
        return "Meet{" +
                "room_id=" + room_id +
                ", room_name='" + room_name + '\'' +
                ", TeacherID=" + TeacherID +
                '}';
    }
}

