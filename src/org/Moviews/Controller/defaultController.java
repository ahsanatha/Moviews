/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import javax.swing.JFrame;
import org.Moviews.Model.Movies;
import org.Moviews.Model.User;

/**
 *
 * @author TSR
 */
public abstract class defaultController {

    protected User user ;

    protected Movies movie;
    
    
    //protected JFrame view = null;

    public void setUser(User user) {
        this.user = user;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }


}
