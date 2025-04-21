/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.moviematefx;

/**
 *
 * @author georg
 */

import java.util.List;
import java.util.ArrayList;


public class RecommendationEngine {
    private List<Movie> movies;


    public RecommendationEngine() {
        movies = new ArrayList<>();
        loadMovies();
    }

    private void loadMovies() {
        movies.add(new Movie("Mad Max: Fury Road", "Action", 2015, 8.1, "George Miller"));
        movies.add(new Movie("John Wick", "Action", 2014, 7.4, "Chad Stahelski"));
        movies.add(new Movie("The Dark Knight", "Action", 2008, 9.0, "Christopher Nolan"));
        movies.add(new Movie("The Shawshank Redemption", "Drama", 1994, 9.3, "Frank Darabont"));
        movies.add(new Movie("Forrest Gump", "Drama", 1994, 8.8, "Robert Zemeckis"));
        movies.add(new Movie("The Green Mile", "Drama", 1999, 8.6, "Frank Darabont"));
        movies.add(new Movie("The Hangover", "Comedy", 2009, 7.7, "Todd Phillips"));
        movies.add(new Movie("Mean Girls", "Comedy", 2004, 7.1, "Mark Waters"));
        movies.add(new Movie("Superbad", "Comedy", 2007, 7.6, "Greg Mottola"));
        movies.add(new Movie("The Conjuring", "Horror", 2013, 7.5, "James Wan"));
        movies.add(new Movie("Get Out", "Horror", 2017, 7.7, "Jordan Peele"));
        movies.add(new Movie("A Quiet Place", "Horror", 2018, 7.5, "John Krasinski"));
        movies.add(new Movie("Interstellar", "Sci-Fi", 2014, 8.6, "Christopher Nolan"));
        movies.add(new Movie("The Matrix", "Sci-Fi", 1999, 8.7, "Lana Wachowski, Lilly Wachowski"));
        movies.add(new Movie("Inception", "Sci-Fi", 2010, 8.8, "Christopher Nolan"));
        movies.add(new Movie("Pride and Prejudice", "Romance", 2005, 7.8, "Joe Wright"));
        movies.add(new Movie("La La Land", "Romance", 2016, 8.0, "Damien Chazelle"));
        movies.add(new Movie("The Notebook", "Romance", 2004, 7.8, "Nick Cassavetes"));
        movies.add(new Movie("Gone Girl", "Thriller", 2014, 8.1, "David Fincher"));
        movies.add(new Movie("Se7en", "Thriller", 1995, 8.6, "David Fincher"));
        movies.add(new Movie("Shutter Island", "Thriller", 2010, 8.2, "Martin Scorsese"));

    }

    public List<Movie> recommendMovies(User user) {
        List<Movie> recommendations = new ArrayList<>();

        for (Movie movie : movies) {
            if (user.getFavoriteGenres().contains(movie.getGenre())) {
                recommendations.add(movie);
            }
        }

        return recommendations;
    }
}
    


