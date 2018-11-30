/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.Moviews.Database.Database;

/**
 *
 * @author TSR
 */
public class UserMovies extends Model{
    public User user;
    public Movies movies;
    
    public String id_retrev;
    public String id_mov;
    public String id_user;
    public double rating_user;
    public String review_user;

    public UserMovies(User user, Movies movies) {
        this.user = user;
        this.movies = movies;
    }

    public UserMovies() {
        
    }

    public String getId_retrev() {
        return id_retrev;
    }

    public void setId_retrev(String id_retrev) {
        this.id_retrev = id_retrev;
    }

    public String getId_mov() {
        return id_mov;
    }

    public void setId_mov(String id_mov) {
        this.id_mov = id_mov;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public double getRating_user() {
        return rating_user;
    }

    public void setRating_user(double rating_user) {
        this.rating_user = rating_user;
    }

    public String getReview_user() {
        return review_user;
    }

    public void setReview_user(String review_user) {
        this.review_user = review_user;
    }

    @Override
    public Object findData(String id) {
        return null;
    }

    public void addRatRev(UserMovies rr) {
        Database db = new Database();
        db.Connect();
        String query = "INSERT INTO `usermovies` VALUES('";
        query += rr.getId_retrev()+"','";
        query += rr.getId_mov()+"','";
        query += rr.getId_user()+"','";
        query += String.valueOf(rr.getRating_user())+"','";
        query += rr.getReview_user()+"')";
        System.out.println(query);
        if(db.Manipulate(query)){
            System.out.println("Data berhasil di tambahkan ke database!");
        }else {
            System.out.println("Data gagal di tambahkan ke database.");
        }
        db.Disconnect();
    }

    public void updateData(Movies m) {
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
    
    public UserMovies cekReview(String id_mov, String id_user){
        UserMovies um = null;
        boolean cek = false;
        Database db = new Database();
        db.Connect();
        String query = "SELECT * FROM `usermovies` WHERE `id_mov`='"+id_mov+"' and `id_user`='"+id_user+"'";
        db.setRs(query);
        ResultSet rs = db.getRs();
        try {
            if(!db.isRsEmpty(rs)){
                um = new UserMovies();
                while(rs.next()){
                    um.setId_retrev(rs.getString("id_ratrev"));
                    um.setId_mov(rs.getString("id_mov"));
                    um.setId_user(rs.getString("id_user"));
                    um.setRating_user(rs.getDouble("rating_user"));
                    um.setReview_user(rs.getString("review_user"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMovies.class.getName()).log(Level.SEVERE, null, ex);
        }
        return um;
    }
    
    public DefaultListModel loadReview(String id_mov) throws SQLException{
        DefaultListModel dlm = new DefaultListModel();
        dlm.clear();
        Database db = new Database();
        db.Connect();
        String query = "SELECT * FROM `usermovies` JOIN `user` USING (id_user) JOIN `movies` USING (id_mov) WHERE `id_mov`='"+id_mov+"';";
        System.out.println(query);
        db.setRs(query);
        ResultSet rs = db.getRs();
        while(rs.next()){
            String rev = rs.getString("username")+" | "+String.valueOf(rs.getDouble("rating_user"))+" | "+rs.getString("review_user");
            dlm.addElement(rev);
        }
        db.Disconnect();
        System.out.println("done load review");
        return dlm;
    }
    
    
    
}
