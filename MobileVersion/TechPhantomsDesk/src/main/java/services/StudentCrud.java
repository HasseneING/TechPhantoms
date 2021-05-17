/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Connection.MyConnection;
import Entities.Student;
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
public class StudentCrud {

    public static ObservableList<Student> GetStudents() {
        ObservableList<Student> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM student";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("cin"), rs.getString("status"), rs.getDate("date_birth"), rs.getTimestamp("date_add"), rs.getString("profile_pic"), rs.getString("state"), rs.getInt("phone_number"), rs.getInt("nb_formation_enrg"), rs.getString("active_formation"), rs.getString("finished_formation"), rs.getInt("schedule")));
            }

            System.out.println("Student list generated!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static int GetStudentID(Student s) {
        int getID=0;
        String requete = "SELECT id FROM student where username = ? and email = ? and first_name = ? and last_name = ?";

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
    
    
    
    
        public static Student GetStudent(String username,String password) {
        Student s=null;
        String requete = "SELECT * FROM student where username = ? and password = ?";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                s= new Student(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("cin"), rs.getString("status"), rs.getDate("date_birth"), rs.getTimestamp("date_add"), rs.getString("profile_pic"), rs.getString("state"), rs.getInt("phone_number"), rs.getInt("nb_formation_enrg"), rs.getString("active_formation"), rs.getString("finished_formation"), rs.getInt("schedule"));
            }
            System.out.println("Student ID generated!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void addStudent(Student s) {
        try {
            String requete = " INSERT INTO student (username, email, password, first_name, last_name, cin, "
                    + "status, date_birth, date_add, profile_pic, state, phone_number) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, s.getUsername());
            pst.setString(2, s.getEmail());
            pst.setString(3, s.getPassword());
            pst.setString(4, s.getFirst_name());
            pst.setString(5, s.getLast_name());
            pst.setInt(6, s.getCin());
            pst.setString(7, s.getStatus());
            pst.setDate(8, s.getDate_birth());
            pst.setTimestamp(9, s.getDate_add());
            pst.setString(10, s.getProfile_pic());
            pst.setString(11, s.getState());
            pst.setInt(12, s.getPhone_number());;
            pst.executeUpdate();
            showAlert("Information dialog", "Student added!\nA verification code is sent to your accout");
            System.out.println("Student added!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void modStudent(Student s) {
        try {
            String requete = " UPDATE student SET username = ?, email = ?, password = ?, first_name = ?, last_name = ?,"
                    + " cin = ?, date_birth = ?, phone_number = ? "
                    + "where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(9, s.getId());
            pst.setString(1, s.getUsername());
            pst.setString(2, s.getEmail());
            pst.setString(3, s.getPassword());
            pst.setString(4, s.getFirst_name());
            pst.setString(5, s.getLast_name());
            pst.setInt(6, s.getCin());
            pst.setDate(7, s.getDate_birth());
            pst.setInt(8, s.getPhone_number());

            pst.executeUpdate();
            showAlert("Information dialog", "Student updated!");
            System.out.println("Student modified!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ChangeStatus2Active(int id) {
        try {
            String requete = " UPDATE student SET status = ? where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            System.out.println(id);
            pst.setString(1, "Active");
            pst.setInt(2, id);

            pst.executeUpdate();
            showAlert("Information dialog", "Student became active!");
            System.out.println("Student became active!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delStudent(int id) {
        try {
            String requete = "DELETE FROM student where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(1, id);
            pst.executeUpdate();
            showAlert("Information dialog", "Student deleted!");
            System.out.println("Student deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
