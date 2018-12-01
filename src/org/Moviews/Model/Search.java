/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.Moviews.Database.Database;

/**
 *
 * @author TSR
 */
public class Search extends Model{

    @Override
    public Movies findData(String title) {
        //find by title.
        Movies m = null;
        try {
            Database db = new Database();
            db.Connect();
            String query = "SELECT * FROM `movies` WHERE `title` LIKE '"+title+"'";
            System.out.println(query);
            db.setRs(query);
            ResultSet rs = db.getRs();
            if(!db.isRsEmpty(rs)){
                while(rs.next()){
                    m = new Movies(
                            rs.getString("id_mov"),
                            rs.getString("title"),
                            rs.getString("sinopsis"),
                            rs.getDate("release"),
                            rs.getInt("duration"),
                            rs.getString("director"),
                            rs.getString("studio"),
                            rs.getDouble("ratingfilm")
                    );
                }
            }else{
                System.out.println("ID tidak di temukan di database!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public Movies getMovieInfo(String id_mov){
        return findData(id_mov);
    }
    
    
}
