/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import org.Moviews.Model.User;
import org.Moviews.View.ViewSignUp;

/**
 *
 * @author TSR
 */
public class ControllerSignUp {
    private User model;
    private ViewSignUp view;

    public ControllerSignUp(User model, ViewSignUp view) {
        this.model = model;
        this.view = view;
    }
    
    public void showView(){
        view.show();
    }
    
}
