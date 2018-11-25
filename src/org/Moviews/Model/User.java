/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Model;
import org.Moviews.Database.Database;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.Moviews.Database.Database;

/**
 *
 * @author TSR
 */
public class User {
    private String id_user;
    private String nama_lengkap;
    private char tipe;
    private char jenis_kelamin;
    private String password;
    private String username;
    private Date tgl_lahir;
    private String tempat_lahir;
    private int JUser;
    

    public User(String nama_lengkap, char tipe, char jenis_kelamin, String password, String username, Date tgl_lahir, String tempat_lahir) {
        this.nama_lengkap = nama_lengkap;
        this.tipe = tipe;
        this.jenis_kelamin = jenis_kelamin;
        this.password = password;
        this.username = username;
        this.tgl_lahir = tgl_lahir;
        this.tempat_lahir = tempat_lahir;
    }

    public User() {
    }


    public String getId_user() {
        return id_user;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public char getTipe() {
        return tipe;
    }

    public char getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public void setJenis_kelamin(char jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }
    
    
    
    public boolean checkUser(String uname, String pass) throws SQLException{
        boolean valid = false;
        Database db = new Database();
        db.Connect();
        String query = "SELECT * FROM `user` WHERE `username` LIKE '"+uname+"'";
        System.out.println(query);
        db.setRs(query);
        if (db.isRsEmpty(db.getRs())) {
            System.out.println("Username not found");
        }else {
            while(db.getRs().next()){
                if(db.getRs().getString("password").equals(pass)){
                    valid = true;
                }else{
                    System.out.println("Wrong Password");
                }
            }
        }
        db.Disconnect();
        return valid;
    }
    
    public int getCurrentId() throws SQLException{
        int x = 0;
        String query = "SELECT COUNT(id_user) FROM `user`";
        Database db = new Database();
        db.Connect();
        System.out.println(query);
        db.setRs(query);
        if(!db.isRsEmpty(db.getRs())){
            while(db.getRs().next()){
                this.JUser = db.getRs().getInt("COUNT(id_user)");
                System.out.println("Banyak user : "+this.JUser);
                x = this.JUser;
            }
        }
        db.Disconnect();
        return x;
    }
    
    public void addUser(User u) throws SQLException{
        String query = "INSERT INTO `user` VALUES ('";
        query += "USER"+(getCurrentId()+1)  +"','";
        query += u.getNama_lengkap() +"','";
        query += u.getTipe() + "','";
        query += u.getJenis_kelamin() + "','";
        query += u.getPassword() + "','";
        query += u.getUsername() + "','";
        query += u.getTgl_lahir() + "','";
        query += u.getTempat_lahir() + "')";
        Database db = new Database();
        db.Connect();
        System.out.println(query);
        if(db.Manipulate(query)){
            System.out.println("Add Value Succes!");
        }else{
            System.out.println("Add Value Failed.");
        } 
    }
    
 
    
}
