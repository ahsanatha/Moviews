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
import org.Moviews.View.ViewMovieList;
import org.Moviews.View.ViewMoviePage;

/**
 *
 * @author TSR
 */
public class ControllerMoviePage extends defaultController {

    private ViewMoviePage view;
    //private Movies movie;
    private UserMovies model;
    private boolean pernahReview = false;

    public ControllerMoviePage(ViewMoviePage view, UserMovies model) {
        this.view = view;
        this.model = model;

        try {
            //load review
            this.view.setListReview(model.loadReview(movie.getId_mov()));
        } catch (SQLException ex) {
            Logger.getLogger(ControllerMoviePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        //untuk mengecek apakah user pernah memberikan review di suatu film, jika iya, maka tampilkan reviewnya di kotak review
        UserMovies um = model.cekReview(movie.getId_mov(), user.getId_user());
        if (um != null) {
            this.view.setReview(um.getReview_user());
            this.view.setRating(um.getRating_user());
            //System.out.println("udah pernah review, id:"+um.getId_retrev());
            this.pernahReview = true;
        }

        //submit Review Listener
        this.view.setSubmitEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submitRatRev();
                    toMovieList();
                    //System.out.println("saya mendengar");
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerMoviePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerMoviePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.view.setHomeEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toHomeCall();
                closeView();
            }
        });
        
        this.view.setSearchEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCall();
                closeView();
            }
        });
    }
    public void toHomeCall(){
        toHome();
    }
    public void searchCall(){
        search(this.view.getSearchBox());
    }
    
    public void closeView(){
        this.view.dispose();
    }

    public void submitRatRev() throws SQLException, ParseException {
        addReviewRating(movie.getId_mov(), user.getId_user());
    }

    public void ShowView() {
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }

    public void addReviewRating(String id_mov, String id_user) throws SQLException, ParseException {
        UserMovies rr = this.model;
        if(!this.pernahReview){
            rr.setId_retrev("RR" + String.valueOf(rr.getCurrentId("usermovies", "id_ratrev", "RR")));            
        }else{
            rr.setId_retrev(model.cekReview(id_mov, id_user).getId_retrev());
        }
        rr.setId_mov(id_mov);
        rr.setId_user(id_user);
        rr.setRating_user(this.view.getRate());

        //String to double
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols sfs = new DecimalFormatSymbols();
        sfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(sfs);
        double d = df.parse(this.view.getLbRate()).doubleValue();
        this.movie.setRatingfilm((d + this.view.getRate()) / 2);

        rr.setReview_user(this.view.getReview());
        if(!this.pernahReview){
           this.model.addRatRev(rr);
        }else{
           this.model.updateData(rr);
        }
        System.out.println("done");
    }

    public void toMovieList() throws SQLException {
        this.movie = null;
        ControllerMovieList mov = new ControllerMovieList(new ViewMovieList(), new Movies());
        mov.ShowView();
        this.view.dispose();
    }

}
