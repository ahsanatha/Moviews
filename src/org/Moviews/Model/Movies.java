/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.Moviews.Database.Database;

/**
 *
 * @author TSR
 */
public class Movies {
    private String id_mov;
    private String title;
    private String sinopsis;
    private Date release;
    private int duration;
    private String director;
    private String studio;
    private double ratingfilm;
    private int JMov;

    public Movies(String id_mov, String title, String sinopsis, Date release, int duration, String director, String studio, double ratingfilm) {
        this.id_mov = id_mov;
        this.title = title;
        this.sinopsis = sinopsis;
        this.release = release;
        this.duration = duration;
        this.director = director;
        this.studio = studio;
        this.ratingfilm = ratingfilm;
    }

    public Movies(String id_mov, String title) {
        this.id_mov = id_mov;
        this.title = title;
    }

    public Movies() {
        
    }

    
    public String getId_mov() {
        return id_mov;
    }

    public void setId_mov(String id_mov) {
        this.id_mov = id_mov;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public double getRatingfilm() {
        return ratingfilm;
    }

    public void setRatingfilm(double ratingfilm) {
        this.ratingfilm = ratingfilm;
    }
    
    public int getMovId() throws SQLException{
        Database db = new Database();
        db.Connect();
        int x =1;
        String query = "SELECT id_mov FROM `movies` WHERE id_mov = 'MOV"+String.valueOf(x)+"'";
        System.out.println(query);
        boolean m = isExist("MOV"+(String.valueOf(x)));
        while(m){
            x = x+1;
            //System.out.println(x);
            m = isExist("MOV"+(String.valueOf(x)));
        }
        db.Disconnect();
        return x;
    }
    
    public ArrayList<Movies> getAllMovies() throws SQLException{
        ArrayList<Movies> arm = new ArrayList<>();
        Database db = new Database();
        db.Connect();
        String query = "SELECT * FROM `movies`";
        System.out.println(query);
        db.setRs(query);
        if(!db.isRsEmpty(db.getRs())){
            ResultSet rs = db.getRs();
            while(db.getRs().next()){
                Movies m = new Movies(
                        rs.getString("id_mov"),
                        rs.getString("title"),
                        rs.getString("sinopsis"),
                        rs.getDate("release"),
                        rs.getInt("duration"),
                        rs.getString("director"),
                        rs.getString("studio"),
                        rs.getDouble("ratingfilm")
                );
                arm.add(m);
            }
        }else{
            System.out.println("DATABASE MOVIES KOSONG!");
        }
        return arm;        
    }
    
    public void addMovies(Movies m){
        Database db = new Database();
        db.Connect();
        String query = "INSERT INTO `movies` VALUES('";
        query += m.getId_mov()+"','";
        query += m.getTitle()+"','";
        query += m.getSinopsis()+"','";
        query += m.getRelease()+"','";
        query += m.getDuration()+"','";
        query += m.getDirector()+"','";
        query += m.getStudio()+"','";
        query += m.getRatingfilm()+"')";
        System.out.println(query);
        if(db.Manipulate(query)){
            System.out.println("Data berhasil di tambahkan ke database!");
        }else {
            System.out.println("Data gagal di tambahkan ke database.");
        }
        db.Disconnect();
    }

    public void updateMovies(Movies m) {
        Database db = new Database();
        db.Connect();
        String query = "UPDATE `movies` SET ";
        query += "`title`= '"+m.getTitle()+"',";
        query += "`sinopsis`= '"+m.getSinopsis()+"',";
        query += "`release`= '"+m.getRelease()+"',";
        query += "`duration`= "+m.getDuration()+",";
        query += "`director`= '"+m.getDirector()+"',";
        query += "`studio`= '"+m.getStudio()+"',";
        query += "`ratingfilm`= "+m.getRatingfilm()+"";
        query += " WHERE `id_mov` = '"+m.getId_mov()+"';";
        System.out.println(query);
        if(db.Manipulate(query)){
            System.out.println("Data berhasil di update ke database!");
        }else {
            System.out.println("Data gagal di update ke database.");
        }
        db.Disconnect();
    }

    public Movies find(String id) throws SQLException {
        Movies m = null;
        Database db = new Database();
        db.Connect();
        String query = "SELECT * FROM `movies` WHERE `id_mov` = '"+id+"';";
        //System.out.println(query);
        db.setRs(query);
        ResultSet rs = db.getRs();
        if(!db.isRsEmpty(rs)){
            while(rs.next()){
                m = new Movies(
                        "MOV"+getMovId(),
                        rs.getString("title"),
                        rs.getString("sinopsis"),
                        rs.getDate("release"),
                        rs.getInt("duration"),
                        rs.getString("director"),
                        rs.getString("studio"),
                        rs.getDouble("ratingfilm")
                );
            }
        }else{
            System.out.println("ID tidak di temukan di database!");
        }
        return m;
    }
    
    public void Delete(String id){
        Database db = new Database();
        db.Connect();
        String query = "DELETE FROM `movies` WHERE  `id_mov`='"+id+"';";
        System.out.println(query);
        if(!db.Manipulate(query)){
            System.out.println("Data "+id+"telah di hapus.");
        }else{
            System.out.println("Data gagal di hapus.");
        }
    }

    private boolean isExist(String id) throws SQLException {
        boolean cek = false;
        
        Database db = new Database();
        db.Connect();
        String query = "SELECT * FROM `movies` WHERE `id_mov` = '"+id+"';";
        db.setRs(query);
        ResultSet rs = db.getRs();
        if(!db.isRsEmpty(db.getRs())){
            cek = true;
        }
        return cek;
    }
}
