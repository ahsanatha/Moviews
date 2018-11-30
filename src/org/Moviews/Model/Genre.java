/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Model;

/**
 *
 * @author GL553-VD
 */
public class Genre {
    private String idGenre;
    private String namaGenre;

    public Genre(String idGenre, String namaGenre) {
        this.idGenre = idGenre;
        this.namaGenre = namaGenre;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getNamaGenre() {
        return namaGenre;
    }

    public void setNamaGenre(String namaGenre) {
        this.namaGenre = namaGenre;
    }
    
    
}
