/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Controller;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import org.Moviews.Model.Home;
import org.Moviews.Model.Movies;
import org.Moviews.Model.User;
import org.Moviews.View.ViewHome;

/**
 *
 * @author TSR
 */
public abstract class defaultController {

    protected static User user ;

    protected static Movies movie;
    private Home h = new Home();
    //protected JFrame view = null;
    protected void toHome(){
        ControllerHome home = new ControllerHome(new ViewHome(), new Home());
        home.showView();
        
    }
    
    protected void search(String key){
        ArrayList<Movies> arm = h.findData(key);
        DefaultListModel dlm = new DefaultListModel();
        for (Movies x : arm){
            dlm.addElement(x.getTitle());
        }
        ControllerSearchResult sr = new ControllerSearchResult(dlm);
        sr.showView();
    }

}
