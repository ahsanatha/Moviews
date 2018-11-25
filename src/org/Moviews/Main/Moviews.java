package org.Moviews.Main;

import org.Moviews.Controller.ControllerSignIn;
import org.Moviews.Model.User;
import org.Moviews.View.ViewSignIn;

/**
 * @author Muhammad Ahsan Athallah & Muhammad Zikri Syahbani
 */
public class Moviews {
    

    public static void main(String[] args) {
        ControllerSignIn in = new ControllerSignIn(new ViewSignIn(), new User());
        in.showView();
    }
}
