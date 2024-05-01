package com.explorehub.explorehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class BlogDetailsController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private TextArea contentArea;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private Stage stage;
    private Blog blog;

    // Method to set the blog data
    public void setBlogData(Blog blog) {
        this.blog = blog;
        titleLabel.setText(blog.getTitle());
        authorLabel.setText("Author: " + blog.getAuthor());
        contentArea.setText(blog.getContent());
    }

    // Method to set the stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Method to handle update button click
    @FXML
    private void handleUpdate() {
        openUpdateForm();
        showAlert(Alert.AlertType.INFORMATION, "Blog Updated", "The blog has been updated successfully.");
        stage.close();
    }

    // Method to handle delete button click
    @FXML
    private void handleDelete() {
        BlogDAO.deleteBlog(blog.getId());
        showAlert(Alert.AlertType.INFORMATION, "Blog Deleted", "The blog has been deleted successfully.");
        stage.close();
    }

    // Method to open the update form
    private void openUpdateForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateBlog.fxml"));
            Parent root = loader.load();
            UpdateBlogController updateBlogController = loader.getController();
            updateBlogController.setBlogToUpdate(blog);
            updateBlogController.setOnBlogUpdatedListener(event -> {
                // Refresh blog details after update
                setBlogData(blog);
            });
            Stage updateStage = new Stage();
            updateStage.setTitle("Update Blog");
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.show();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    // Method to show alert
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
