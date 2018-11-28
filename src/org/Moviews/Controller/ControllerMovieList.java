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
import javax.swing.table.DefaultTableModel;
import org.Moviews.Model.Movies;
import org.Moviews.View.ViewManipulateMovie;
import org.Moviews.View.ViewMovieList;

/**
 *
 * @author TSR
 */
public class ControllerMovieList {
    private ViewMovieList view;
    private Movies model;

    public ControllerMovieList(ViewMovieList view, Movies model) throws SQLException {
        this.view = view;
        this.model = model;
        loadMovies();
        
        this.view.setAddEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    toAddMovie();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerMovieList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.view.setDeleteEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteMovie();
                try {
                    loadMovies();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerMovieList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.view.setEditEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(getMovieSelectedInfo() != null){
                        try {
                            toEditMovie(getMovieSelectedInfo());
                        } catch (SQLException ex) {
                            Logger.getLogger(ControllerMovieList.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        System.out.println("Tidak ada baris yang dipilih");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerMovieList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.view.setRefreshEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadMovies();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerMovieList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
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
    
    public void toAddMovie() throws SQLException{
        ControllerMovie am = new ControllerMovie(new ViewManipulateMovie(), new Movies(), 'c');
        am.showView();
        this.view.dispose();
    }
    
    public void toEditMovie(Movies m) throws SQLException{
        ViewManipulateMovie Eview = new ViewManipulateMovie();
        
        Eview.setTitle(m.getTitle());
        Eview.setDuration(m.getDuration());
        Eview.setTanggal(String.valueOf(m.getRelease().getDay()));
        Eview.setBulan(String.valueOf(m.getRelease().getMonth()));
        Eview.setTahun(String.valueOf(m.getRelease().getYear()));
        Eview.setDirector(m.getDirector());
        Eview.setStudio(m.getStudio());
        Eview.setSinopsis(m.getSinopsis());
        Eview.setRating(String.valueOf(m.getRatingfilm()));
        
        ControllerMovie em = new ControllerMovie(Eview, new Movies(), 'u');
        em.setIdSelected(m.getId_mov());
        em.showView();
        this.view.dispose();
    }
    
    public Movies getMovieSelectedInfo() throws SQLException{
        String id = this.view.getSelectedMovies();
        Movies m = this.model.find(id);
        return m;
    }
    
    public void DeleteMovie(){
        this.model.Delete(this.view.getSelectedMovies());
    }
    
    public void openMov(){
        
    }
}
