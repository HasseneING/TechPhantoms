/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SAFA
 */
public class MyConnection {

    public String url = "jdbc:mysql://localhost:3306/zoom";
    public String login = "root";
    public String pwd = "";
    public java.sql.Connection cn;

    public MyConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erreur de connexion");
            System.out.println(ex.getMessage());
        }
    }

    public String encrypt(String password) throws NoSuchAlgorithmException {
//        String crypte = "";
//        for (int i = 0; i < password.length(); i++) {
//            int c = password.charAt(i) ^ 48;
//            crypte +=(char) c;
//        }
//        return crypte;
        MessageDigest md;

            md = MessageDigest.getInstance("MD5");
            byte byteData[] = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String crypte = new String(hexString);
            return crypte;
        
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MyConnection conn = new MyConnection();
        System.out.println("zzz");

        System.out.println(conn.encrypt("XER"));
    }
}
