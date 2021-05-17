package utils;


import model.Student;

/**
 *
 * @author Anis
 */
public class UserSession {
     public static UserSession instance;

    private Student u;

    public Student getU() {
        return u;
    }

    public void setU(Student u){
        this.u=u;
    }

   

    @Override
    public String toString() {
        return "UserSession{" +
                "u=" + u +
                '}';
    }

    public UserSession(Student u) {
        this.u = u;

    }

    public static UserSession getInstance(Student u) {
        if(instance == null) {
            instance = new UserSession(u);
        }
        return instance;
    }

    

    public void cleanUserSession() {
        instance=null;
    }


    }

