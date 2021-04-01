
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {

    private String url="jdbc:mysql://127.0.0.1:3306/pidev";
    private String login="root";
    private String pwd="";

    private Connection cnx;

    private static DataSource instance;

    public DataSource() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DataSource getInstance(){
        if(instance==null)
            instance=new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }



}
