/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.moviematefx;

/**
 *
 * @author georg
 */

import java.util.Scanner;
import java.util.List;





public class MovieMate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        // Create User
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);

        // Get user preferences
        System.out.println("Choose your favorite genres (Action, Drama, Comedy, Horror, Sci-Fi, Romance, Thriller). Type 'done' to finish:");
        while (true) {
            String genre = scanner.nextLine();
            if (genre.equalsIgnoreCase("done")) {
                break;
            }
            user.addFavoriteGenre(genre);
        }

        // Generate recommendations
        RecommendationEngine engine = new RecommendationEngine();
        List<Movie> recommendations = engine.recommendMovies(user);

        // Display recommendations
        System.out.println("\nRecommended movies for " + user.getName() + ":");
        for (Movie movie : recommendations) {
            System.out.println("- " + movie.getDetails());
        }

        scanner.close();
    }
}
    
    

