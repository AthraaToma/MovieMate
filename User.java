/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.moviematefx;

/**
 *
 * @author georg
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



public class User {
   private String name;
    private List<String> favoriteGenres;

    public User(String name) {
        this.name = name;
        this.favoriteGenres = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void addFavoriteGenre(String genre) {
        if (!favoriteGenres.contains(genre)) {
            favoriteGenres.add(genre);
        }
    }

    public void savePreferences() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(name + "_preferences.txt"))) {
            for (String genre : favoriteGenres) {
                writer.println(genre);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loadPreferences(String userName) {
        User user = new User(userName);
        File file = new File(userName + "_preferences.txt");

        if (!file.exists()) {
            return user; // Return a new user if no previous preferences are found
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                user.addFavoriteGenre(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}

