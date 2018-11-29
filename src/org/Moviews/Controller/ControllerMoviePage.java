/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import org.Moviews.Model.Movies;
import org.Moviews.View.ViewMoviePage;

/**
 *
 * @author TSR
 */
public class ControllerMoviePage extends defaultController {
    private ViewMoviePage view;
    private Movies model;

    public ControllerMoviePage(ViewMoviePage view, Movies model) {
        this.view = view;
        this.model = model;
        this.movie = model;
    }

    public void ShowView(){
        this.view.setLocationRelativeTo(null);
        this.view.show();
    }
    
    
}
