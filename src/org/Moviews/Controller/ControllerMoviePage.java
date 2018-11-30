/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.Moviews.Database.Database;
import org.Moviews.Model.Movies;
import org.Moviews.Model.UserMovies;
import org.Moviews.View.ViewMoviePage;

/**
 *
 * @author TSR
 */
public class ControllerMoviePage extends defaultController {
    private ViewMoviePage view;
    //private Movies movie;
    private UserMovies model;

    public ControllerMoviePage(ViewMoviePage view, UserMovies model) {
        this.view = view;
        this.model = model;
        
        try {
            //load review
            System.out.println(movie.getId_mov());
            this.view.setListReview(model.loadReview(movie.getId_mov()));
        } catch (SQLException ex) {
            Logger.getLogger(ControllerMoviePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //untuk mengecek apakah user pernah memberikan review di suatu film, jika iya, maka tampilkan reviewnya di kotak review
        pernahReview();
        
        //submit Review Listener
        this.view.setSubmitEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submitRatRev();
                    //System.out.println("saya mendengar");
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerMoviePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerMoviePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void submitRatRev() throws SQLException, ParseException{
        //System.out.println("haha : "+movie.getId_mov()+" "+user.getNama_lengkap());
        addReviewRating(movie.getId_mov(),user.getId_user());
    }
    
    public void ShowView(){
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }
    
    public void addReviewRating(String id_mov, String id_user) throws SQLException, ParseException{
        UserMovies rr = this.model;
        rr.setId_retrev("RR"+String.valueOf(rr.getCurrentId("usermovies", "id_ratrev", "RR")));
        rr.setId_mov(id_mov);
        rr.setId_user(id_user);
        rr.setRating_user(this.view.getRate());
        
        //String to double
        DecimalFormat df = new DecimalFormat(); 
        DecimalFormatSymbols sfs = new DecimalFormatSymbols();
        sfs.setDecimalSeparator('.'); 
        df.setDecimalFormatSymbols(sfs);
        double d = df.parse(this.view.getLbRate()).doubleValue();
        this.movie.setRatingfilm((d+this.view.getRate())/2);
        
        rr.setReview_user(this.view.getReview());
        this.model.addRatRev(rr);
        System.out.println("done");
    }

    public void pernahReview(){
        UserMovies um = model.cekReview(movie.getId_mov(),user.getId_user());
        if( um != null){
            this.view.setReview(um.getReview_user());
            this.view.getRate();
        }
        
    }
    
}
