/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.Moviews.Model.Movies;
import org.Moviews.View.ViewManipulateMovie;
import org.Moviews.View.ViewMovieList;

/**
 *
 * @author TSR
 */
public class ControllerManipulateMovie {
    private ViewManipulateMovie view;
    private Movies model;
    private char status;
    private String idSelected;

    public ControllerManipulateMovie(ViewManipulateMovie view, Movies model, char status) throws SQLException {
        this.view = view;
        this.model = model;
        this.status = status;
        
        this.view.setCancelEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    toMovieList();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerManipulateMovie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.view.setDoneEvent(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
                Movies m = new Movies();
                if(status == 'c'){
                    try {
                        m.setId_mov("MOV"+(model.getMovId()));
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerManipulateMovie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(status == 'u'){
                    m.setId_mov(idSelected);
                }
                m.setDuration(view.getDuration());
                m.setDirector(view.getDirector());
                try {
                    m.setRatingfilm(view.getRating());
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerManipulateMovie.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    m.setRelease(view.getRilis());
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerManipulateMovie.class.getName()).log(Level.SEVERE, null, ex);
                }
                m.setSinopsis(view.getSinopsis());
                m.setStudio(view.getStudio());
                m.setTitle(view.getTitle());
                if (status == 'c'){
                    model.addMovies(m);
                }else if (status == 'u'){
                    model.updateMovies(m);
                }
                try {
                    toMovieList();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerManipulateMovie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
    }
    
    public void toMovieList() throws SQLException{
        ControllerMovieList m = new ControllerMovieList(new ViewMovieList(), new Movies());
        m.ShowView();
        this.view.dispose();
    }
    
   public void showView(){
       this.view.setLocationRelativeTo(null);
       this.view.show();
   }

    public void setIdSelected(String idSelected) {
        this.idSelected = idSelected;
    }
   
   


}
