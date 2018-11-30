/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.Moviews.Model.Home;
import org.Moviews.Model.Movies;
import org.Moviews.Model.User;
import org.Moviews.View.ViewHome;
import org.Moviews.View.ViewMovieList;

/**
 *
 * @author TSR
 */
public class ControllerHome extends defaultController{
    private ViewHome view;
    // PR : apa modelnya home??????
    private Home model;

    public ControllerHome(ViewHome view, Home model) {
        this.view = view;
        this.model = model;
        
        this.view.setMoviesEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    toMovie();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerHome.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        this.view.setSearchBox();
    }
    
    public void toMovie() throws SQLException{
        ControllerMovieList mov = new ControllerMovieList(new ViewMovieList(), new Movies());
        mov.ShowView();
        this.view.dispose();
    }
    
    public void showView(){
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }

    
}
