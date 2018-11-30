/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        this.movie = movie;
        
        this.view.setSubmitEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submitRatRev();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerMoviePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerMoviePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void submitRatRev() throws SQLException, ParseException{
        System.out.println("haha : "+movie.getTitle()+" "+user.getUsername());
        addReviewRating(movie.getId_mov(),user.getId_user());
    }
    
    public void ShowView(){
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }
    
    public void addReviewRating(String id_mov, String id_user) throws SQLException, ParseException{
        UserMovies rr = new UserMovies();
        rr.setId_retrev("RR"+String.valueOf(rr.getCurrentId("usermovies", "id_ratrev", "RR")));
        rr.setId_mov(id_mov);
        rr.setId_user(id_user);
        rr.setRating_user(this.view.getRate());
        
        DecimalFormat df = new DecimalFormat(); 
        DecimalFormatSymbols sfs = new DecimalFormatSymbols();
        sfs.setDecimalSeparator(','); 
        df.setDecimalFormatSymbols(sfs);
        double d = df.parse(this.view.getLbRate()).doubleValue();
        this.movie.setRatingfilm((d+this.view.getRate())/2);
        
        rr.setReview_user(this.view.getReview());
        rr.addRatRev(rr);
    }
    public void loadReview(){
        
    }
    
}
