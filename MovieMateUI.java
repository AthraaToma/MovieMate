package com.mycompany.moviematefx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;


/**
 * JavaFX App
 */


public class MovieMateUI extends Application {
   private TextField nameField;
    private List<CheckBox> genreCheckboxes;
    private ListView<String> recommendationsList;
    private RecommendationEngine recommendationEngine;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        recommendationEngine = new RecommendationEngine();

        // Build and show login scene
        Scene loginScene = buildLoginScene();
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("MovieMate Login");
        primaryStage.show();
    }

    // Build Login Scene
    private Scene buildLoginScene() {
        Label loginLabel = new Label("Enter your username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        Button loginButton = new Button("Login");
        VBox loginLayout = new VBox(10, loginLabel, usernameField, loginButton);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(20));

        loginButton.setOnAction(e -> {
            String userName = usernameField.getText().trim();
            if (userName.isEmpty()) {
                return;
            }

            // Load saved preferences
            User savedUser = User.loadPreferences(userName);

            // Build main UI scene with saved preferences
            Scene mainScene = buildMainScene(savedUser);
            primaryStage.setScene(mainScene);
        });

        return new Scene(loginLayout, 300, 200);
    }

    // Build Main Scene
    private Scene buildMainScene(User user) {
        // Title
    Label titleLabel = new Label("MovieMate - Personalized Movie Recommendations");
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    // User Name
    Label nameLabel = new Label("Enter your name:");
    nameField = new TextField(user.getName());
    nameField.setPromptText("Your Name");

    // Genre Selection
    Label genreLabel = new Label("Select Your Favorite Genres:");
    genreCheckboxes = new ArrayList<>();
    String[] genres = {"Action", "Drama", "Comedy", "Thriller", "Romance", "Sci-Fi", "Horror"};

    for (String genre : genres) {
        CheckBox checkBox = new CheckBox(genre);
        if (user.getFavoriteGenres().contains(genre)) {
            checkBox.setSelected(true);
        }
        genreCheckboxes.add(checkBox);
    }

    VBox genreBox = new VBox(10);
    genreBox.getChildren().addAll(genreCheckboxes);
    genreBox.setPadding(new Insets(10));

    // Recommend Button
    Button recommendButton = new Button("Get Recommendations");
    recommendButton.setOnAction(e -> generateRecommendations());

    // Save Preferences Button
    Button saveButton = new Button("Save Preferences");
    saveButton.setOnAction(e -> {
        User currentUser = new User(nameField.getText().trim());
        for (CheckBox checkBox : genreCheckboxes) {
            if (checkBox.isSelected()) {
                currentUser.addFavoriteGenre(checkBox.getText());
            }
        }
        currentUser.savePreferences();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Preferences Saved");
        alert.setHeaderText(null);
        alert.setContentText("Your preferences have been saved.");
        alert.showAndWait();
    });
    // Clear Selections Button
    Button clearButton = new Button("Clear Selections");
    clearButton.setOnAction(e -> {
        for (CheckBox checkBox : genreCheckboxes) {
            checkBox.setSelected(false);
        }
    });

    // Recommendation Results
    recommendationsList = new ListView<>();

    // Layout
    VBox layout = new VBox(10);
    layout.setStyle("-fx-padding: 20px;");
    layout.getChildren().addAll(
        titleLabel, nameLabel, nameField,
        genreLabel, genreBox,
        recommendButton, saveButton, clearButton,
        recommendationsList 
    );

    Scene scene = new Scene(layout, 400, 500);
    scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
    return scene;
}

    private void generateRecommendations() {
        String userName = nameField.getText().trim();
        if (userName.isEmpty()) {
            recommendationsList.getItems().setAll("Please enter your name.");
            return;
        }

        User user = new User(userName);
        for (CheckBox checkBox : genreCheckboxes) {
            if (checkBox.isSelected()) {
                user.addFavoriteGenre(checkBox.getText());
            }
        }

        // Save preferences
        user.savePreferences();

        // Generate and show recommendations
        List<Movie> recommendations = recommendationEngine.recommendMovies(user);
        recommendationsList.getItems().clear();
        if (recommendations.isEmpty()) {
            recommendationsList.getItems().add("No movies found for selected genres.");
        } else {
            for (Movie movie : recommendations) {
                recommendationsList.getItems().add(movie.getDetails());
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}