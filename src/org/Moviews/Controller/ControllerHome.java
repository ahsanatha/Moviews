/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.Moviews.Model.Home;
import org.Moviews.Model.Movies;
import org.Moviews.Model.User;
import org.Moviews.View.ViewHome;
import org.Moviews.View.ViewMovieList;
import org.Moviews.View.ViewSignIn;

/**
 *
 * @author TSR
 */
public class ControllerHome extends defaultController{
    private ViewHome view;
    private Home model;
    private ArrayList<Movies> arm = new ArrayList<>();
    private Movies m = new Movies();
    private Object o;

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
        
        this.view.setSearchEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arm = model.findData(view.getSearchBox());
                DefaultListModel dlm = new DefaultListModel();
                for (Movies x : arm){
                    dlm.addElement(x.getTitle());
                }
                ControllerSearchResult sr = new ControllerSearchResult(dlm);
                sr.showView();
                closeView();
            }
        });
        
        this.view.setLogOutEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toSignIn();
            }
        });
    }
    public void closeView(){
        this.view.dispose();
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

    public void toSignIn(){
        ControllerSignIn in = new ControllerSignIn(new ViewSignIn(), new User());
        in.showView();
        view.dispose();
        System.out.println("User Logged out.");
        this.user = null;
    }
}
