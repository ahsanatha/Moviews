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

    public ControllerMovies(ViewMovieList view, Movies model) throws SQLException {
        this.view = view;
        this.model = model;
        loadMovies();
    }
    
    public void loadMovies() throws SQLException{
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"ID","Judul","Rating","Tanggal Rilis","Durasi(menit)","Sutradara","Sinopsis"},0);
        ArrayList<Movies> arm = this.model.getAllMovies();
        for (Movies m : arm){
            dtm.addRow(new Object[]{m.getId_mov(),m.getTitle(),m.getRatingfilm(),m.getRelease(),m.getDuration(),m.getDirector(),m.getSinopsis()});
        }
        this.view.setMovies(dtm);
    }
    
    public void ShowView(){
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }
}
