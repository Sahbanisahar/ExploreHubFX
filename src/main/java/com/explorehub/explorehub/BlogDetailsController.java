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

    @FXML
    private Button likeButton;

    @FXML
    private Button dislikeButton;

    private Stage stage;
    private Blog blog;

    // Method to set the blog data
    public void setBlogData(Blog blog) {
        this.blog = blog;
        titleLabel.setText(blog.getTitle());
        authorLabel.setText("Author: " + blog.getAuthor());
        contentArea.setText(blog.getContent());

    // Fetch like and dislike status from the database
        boolean isLiked = BlogDAO.getLikedStatus(blog.getId());
        boolean isDisliked = BlogDAO.getDislikedStatus(blog.getId());

        // Update like and dislike buttons based on database status
        updateButtonStyle(likeButton, isLiked);
        updateButtonStyle(dislikeButton, isDisliked);
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
            e.printStackTrace();
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
    @FXML
    private void handleLike() {
        if (!blog.isLiked()) {
            BlogDAO.updateLikedStatus(blog.getId(), true);
            blog.setLiked(true);
            updateButtonStyle(likeButton, true);

            // If the blog was previously disliked, update its status
            if (blog.isDisliked()) {
                BlogDAO.updateDislikedStatus(blog.getId(), false);
                blog.setDisliked(false);
                updateButtonStyle(dislikeButton, false);
            }
        } else {
            // If already liked, remove the like
            BlogDAO.updateLikedStatus(blog.getId(), false);
            blog.setLiked(false);
            updateButtonStyle(likeButton, false);
        }
    }
    @FXML
    private void handleDislike() {
        if (!blog.isDisliked()) {
            BlogDAO.updateDislikedStatus(blog.getId(), true);
            blog.setDisliked(true);
            updateButtonStyle(dislikeButton, true);

            // If the blog was previously liked, update its status
            if (blog.isLiked()) {
                BlogDAO.updateLikedStatus(blog.getId(), false);
                blog.setLiked(false);
                updateButtonStyle(likeButton, false);
            }
        } else {
            // If already disliked, remove the dislike
            BlogDAO.updateDislikedStatus(blog.getId(), false);
            blog.setDisliked(false);
            updateButtonStyle(dislikeButton, false);
        }
    }
    private void updateButtonStyle(Button button, boolean isActive) {
        if (isActive) {
            button.setStyle("-fx-background-color: orange; -fx-text-fill: WHITE;");
        } else {
            button.setStyle("-fx-background-color: #b8b8b8; -fx-text-fill: WHITE;");
        }
    }
}
