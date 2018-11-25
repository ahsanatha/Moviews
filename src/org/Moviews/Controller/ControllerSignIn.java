/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.Moviews.Model.User;
import org.Moviews.View.ViewSignIn;

/**
 *
 * @author TSR
 */
public class ControllerSignIn {
    private ViewSignIn view;
    private User model;

    public ControllerSignIn(ViewSignIn view, User model) {
        this.view = view;
        this.model = model;
        //atur sign in
        this.view.setSignInEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(model.checkUser(view.getUname(), view.getPass())){
                        toHome();
                    }else{
                        System.out.println("Login failed");
                        view.reset();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerSignIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        //atur sign up
        this.view.setSignUpEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toSignUp();
            }
            
        });
       
    }

    
    public void showView(){
        view.show();
    }
    
    public void toHome(){
        view.dispose();
    }
    
    public void toSignUp(){
       view.dispose();
    }
    
}
