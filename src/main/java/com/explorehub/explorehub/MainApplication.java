package com.explorehub.explorehub;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file for the ListBlogs scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListBlogs.fxml"));
            Parent root = loader.load();

            // Set up the primary stage
            primaryStage.setTitle("ExploreHub");
            Scene scene = new Scene(root);

            // Set the initial size of the stage
            primaryStage.setScene(scene);
            primaryStage.setWidth(1095);
            primaryStage.setHeight(800);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
