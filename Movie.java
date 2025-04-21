package com.mycompany.moviematefx;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author georg
 */

public class Movie {
    private String title;
    private String genre;
    private int releaseYear;
    private double rating;
    private String director;

    public Movie(String title, String genre, int releaseYear, double rating, String director) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public String getDetails() {
        return title + " (" + releaseYear + "), Rating: " + rating + "/10, Directed by: " + director;
    }
    }



