/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import org.Moviews.Model.Movies;
import org.Moviews.View.ViewAddMovie;

/**
 *
 * @author TSR
 */
class ControllerAddMovie {
    private ViewAddMovie view;
    private Movies model;

    public ControllerAddMovie(ViewAddMovie view, Movies model) {
        this.view = view;
        this.model = model;
    }
    
   public void showView(){
       this.view.setLocationRelativeTo(null);
       this.view.show();
   }
}