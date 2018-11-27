/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.Moviews.Model.Movies;
import org.Moviews.View.ViewMovieList;

/**
 *
 * @author TSR
 */
public class ControllerMovies {
    private ViewMovieList view;
    private Movies model;

    public ControllerMovies(ViewMovieList view, Movies model) {
        this.view = view;
        this.model = model;

    }
    
    public DefaultTableModel loadMovies() throws SQLException{
        DefaultTableModel dtm = new DefaultTableModel();
        ArrayList<Movies> arm = this.model.getAllMovies();
        return dtm;
    }
    
    public void ShowView(){
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }
}
