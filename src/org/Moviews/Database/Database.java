/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TSR
 */
public class Database {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    public Database() {
    }
    
    public void Connect(){
        try {
            String url = "jdbc:mysql://localhost/praktikum";
            String user = "root";
            String pass = "";
            this.conn = DriverManager.getConnection(url, user, pass);
            this.stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Disconnect(){
        try {
            this.conn.close();
            this.stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean Manipulate(String query){
        boolean cek = false;
        try {
            int rows = this.stmt.executeUpdate(query);
            if(rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }
    public boolean checkUser(String uname, String pass){
        boolean valid = false;
        Connect();
        String query = "SELECT * FROM User WHERE 'username' LIKE "+uname;
        if(Manipulate(query)){
            try {
                this.rs = stmt.executeQuery(query);
                if(rs.getString("password") == pass){
                    valid = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return valid;
    }
    
    
}
