/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import org.Moviews.Model.Movies;
import org.Moviews.Model.Search;
import org.Moviews.View.ViewSearchResult;

/**
 *
 * @author TSR
 */
public class ControllerSearchResult {
    private ViewSearchResult view = new ViewSearchResult();
    private Search model = new Search();


    public ControllerSearchResult(DefaultListModel dlm) {
        this.view.setListResult(dlm);
        this.view.SetOpenEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerMovieList ml = new ControllerMovieList();
                ml.toMoviePage(getSelectedMovie());
                closeView();
            }
        });
    }
    
    public void closeView(){
        this.view.dispose();
    }
    
    public Movies getSelectedMovie(){
        String id = this.view.getSelectedMovies();
        return this.model.getMovieInfo(id);
    }
    
    public void showView(){
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }

    
    
    
}
