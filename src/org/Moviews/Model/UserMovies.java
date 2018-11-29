/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Moviews.Model;

/**
 *
 * @author TSR
 */
public class UserMovies {
    public User user;
    public Movies movies;
    
    public String id_retrev;
    
    public String id_mov;
    public String id_user;
    public double rating_user;
    public String review_user;

    public UserMovies(User user, Movies movies) {
        this.user = user;
        this.movies = movies;
    }

    public String getId_retrev() {
        return id_retrev;
    }

    public void setId_retrev(String id_retrev) {
        this.id_retrev = id_retrev;
    }

    public String getId_mov() {
        return id_mov;
    }

    public void setId_mov(String id_mov) {
        this.id_mov = id_mov;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public double getRating_user() {
        return rating_user;
    }

    public void setRating_user(double rating_user) {
        this.rating_user = rating_user;
    }

    public String getReview_user() {
        return review_user;
    }

    public void setReview_user(String review_user) {
        this.review_user = review_user;
    }
    
    
    
    
}
