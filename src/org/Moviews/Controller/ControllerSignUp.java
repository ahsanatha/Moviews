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
import org.Moviews.View.ViewSignUp;

/**
 *
 * @author TSR
 */
public class ControllerSignUp {
    private User model;
    private ViewSignUp view;

    public ControllerSignUp(ViewSignUp view, User model) {
        this.model = model;
        this.view = view;
        
        this.view.setSignUpEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = new User();
                u.setNama_lengkap(view.getFullName());
                u.setJenis_kelamin(view.getJK());
                u.setUsername(view.getUsername());
                u.setPassword(view.getPassword());
                u.setTgl_lahir(view.getTglLahir());
                u.setTempat_lahir(view.getTempatLahir());
                u.setTipe(view.getTP());
                try {
                    model.addUser(u);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerSignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
                toSignIn();
            }
        });
        
        this.view.setCancelEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toSignIn();
            }
        });
        
        System.out.println(this.view.getTanggal());
        System.out.println(this.view.getBulan());
        System.out.println(this.view.getTahun());
        System.out.println(this.view.getTglLahir());
    }
    
    public void showView(){
        view.setLocationRelativeTo(null);
        view.show();
    }
    
    public void toSignIn(){
        ControllerSignIn in = new ControllerSignIn(new ViewSignIn(), new User());
        in.showView();
        view.dispose();
    }
    
}
