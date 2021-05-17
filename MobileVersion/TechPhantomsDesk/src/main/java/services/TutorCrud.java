/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Connection.MyConnection;
import Entities.Tutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SAFA
 */
public class TutorCrud {

    public static ObservableList<Tutor> GetTutors() {
        ObservableList<Tutor> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM tutor";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Tutor(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("cin"), rs.getString("status"), rs.getDate("date_birth"), rs.getTimestamp("date_add"), rs.getString("profile_picture"), rs.getString("state"), rs.getInt("phone_number"), rs.getInt("nb_formation"), rs.getString("certificats"), rs.getInt("nb_student"), rs.getDouble("price_hour"), rs.getInt("schedule"), rs.getString("cv"), rs.getString("video"), rs.getString("subject"), rs.getString("language")));
            }

            System.out.println("Tutor list generated!");
        } catch (SQLException ex) {
            Logger.getLogger(TutorCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static int GetTutorID(Tutor s) {
        int getID = 0;
        String requete = "SELECT id FROM Tutor where username = ? and email = ? and first_name = ? and last_name = ?";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, s.getUsername());
            pst.setString(2, s.getEmail());
            pst.setString(3, s.getFirst_name());
            pst.setString(4, s.getLast_name());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                getID = rs.getInt("id");
            }
            System.out.println("Student ID generated!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getID;
    }

    public static Tutor GetTutor(String username, String password) {
        Tutor t = null;
        String requete = "SELECT * FROM Tutor where username = ? and password = ?";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                t = new Tutor(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("cin"), rs.getString("status"), rs.getDate("date_birth"), rs.getTimestamp("date_add"), rs.getString("profile_picture"), rs.getString("state"), rs.getInt("phone_number"), rs.getInt("nb_formation"), rs.getString("certificats"), rs.getInt("nb_student"), rs.getDouble("price_hour"), rs.getInt("schedule"), rs.getString("cv"), rs.getString("video"), rs.getString("subject"), rs.getString("language"));
            }
            System.out.println("Student ID generated!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public static ObservableList<Tutor> SearchTutors(String username) {
        ObservableList<Tutor> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM tutor where username like '%" + username + "%'";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Tutor(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("cin"), rs.getString("status"), rs.getDate("date_birth"), rs.getTimestamp("date_add"), rs.getString("profile_picture"), rs.getString("state"), rs.getInt("phone_number"), rs.getInt("nb_formation"), rs.getString("certificats"), rs.getInt("nb_student"), rs.getDouble("price_hour"), rs.getInt("schedule"), rs.getString("cv"), rs.getString("video"), rs.getString("subject"), rs.getString("language")));
            }

            System.out.println("Tutor list generated!");
        } catch (SQLException ex) {
            Logger.getLogger(TutorCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addTutor(Tutor t) {
        try {
            String requete = " INSERT INTO tutor (username, email, password, first_name, last_name, cin, "
                    + "status, date_birth, date_add, profile_picture, state, phone_number, "
                    + "certificats, price_hour,cv, video, subject, language) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, t.getUsername());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getFirst_name());
            pst.setString(5, t.getLast_name());
            pst.setInt(6, t.getCin());
            pst.setString(7, t.getStatus());
            pst.setDate(8, t.getDate_birth());
            pst.setTimestamp(9, t.getDate_add());
            pst.setString(10, t.getProfile_pic());
            pst.setString(11, t.getState());
            pst.setInt(12, t.getPhone_number());
            pst.setString(13, t.getCertificats());
            pst.setDouble(14, t.getPrice_hour());
            pst.setString(15, t.getCv());
            pst.setString(16, t.getVideo());
            pst.setString(17, t.getSubject());
            pst.setString(18, t.getLanguage());

            pst.executeUpdate();
            showAlert("Information dialog", "Tutor added!\nA verification code is sent to your accout");
            System.out.println("Tutor added!");
        } catch (SQLException ex) {
            Logger.getLogger(TutorCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void modTutor(Tutor t) {
        try {
            String requete = " UPDATE tutor SET username = ?, email = ?, password = ?, first_name = ?, last_name = ?,"
                    + " cin = ?, status = ?, date_birth = ?, date_add = ?, profile_picture = ?, state = ?, phone_number = ?, nb_formation = ?,"
                    + " certificats = ?, nb_student = ?, price_hour = ? , schedule = ?, cv = ?, video = ?, subject = ?, language = ?  "
                    + "where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(22, t.getId());
            pst.setString(1, t.getUsername());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getFirst_name());
            pst.setString(5, t.getLast_name());
            pst.setInt(6, t.getCin());
            pst.setString(7, t.getStatus());
            pst.setDate(8, t.getDate_birth());
            pst.setTimestamp(9, t.getDate_add());
            pst.setString(10, t.getProfile_pic());
            pst.setString(11, t.getState());
            pst.setInt(12, t.getPhone_number());
            pst.setInt(13, t.getNb_formation());
            pst.setString(14, t.getCertificats());
            pst.setInt(15, t.getNb_student());
            pst.setDouble(16, t.getPrice_hour());
            pst.setInt(17, t.getSchedule());
            pst.setString(18, t.getCv());
            pst.setString(19, t.getVideo());
            pst.setString(20, t.getSubject());
            pst.setString(21, t.getLanguage());

            pst.executeUpdate();
            showAlert("Information dialog", "Tutor Modified!");
            System.out.println("Tutor modified!");
        } catch (SQLException ex) {
            Logger.getLogger(TutorCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delTutor(int id) {
        try {
            String requete = "DELETE FROM tutor where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(1, id);
            pst.executeUpdate();
            showAlert("Information dialog", "Tutor deleted!");
            System.out.println("Tutor deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(TutorCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ChangeStatus2Active(int id) {
        try {
            String requete = " UPDATE Tutor SET status = ? where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            System.out.println(id);
            pst.setString(1, "Active");
            pst.setInt(2, id);

            pst.executeUpdate();
            showAlert("Information dialog", "Student became active!");
            System.out.println("Tutor became active!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
