/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

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
    
    public DefaultTableModel loadMovies(){
        DefaultTableModel dtm = new DefaultTableModel();
        
        
        
        return dtm;
    }
}
