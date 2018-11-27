/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.Moviews.Model.Home;
import org.Moviews.Model.Movies;
import org.Moviews.View.ViewHome;
import org.Moviews.View.ViewMovieList;

/**
 *
 * @author TSR
 */
public class ControllerHome {
    private ViewHome view;
    // PR : apa modelnya home??????
    private Home model;

    public ControllerHome(ViewHome view, Home model) {
        this.view = view;
        this.model = model;
        
        this.view.setMoviesEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toMovie();
            }
            
        });
    }
    
    public void toMovie(){
        ControllerMovies mov = new ControllerMovies(new ViewMovieList(), new Movies());
        mov.ShowView();
        this.view.dispose();
    }
    
    public void showView(){
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }
}
