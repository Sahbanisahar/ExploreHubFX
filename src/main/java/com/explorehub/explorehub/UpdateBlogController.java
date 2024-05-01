package com.explorehub.explorehub;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UpdateBlogController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField coverField;

    @FXML
    private TextField authorField;

    @FXML
    private TextArea contentArea;

    @FXML
    private Button updateButton;

    private Blog blogToUpdate;
    private EventHandler<ActionEvent> onBlogUpdatedListener;

    public void setBlogToUpdate(Blog blog) {
        this.blogToUpdate = blog;
        fillFieldsWithBlogData();
    }

    @FXML
    private void initialize() {
        updateButton.setOnAction(event -> updateBlog());
    }

    public void setOnBlogUpdatedListener(EventHandler<ActionEvent> listener) {
        this.onBlogUpdatedListener = listener;
    }

    private void fillFieldsWithBlogData() {
        if (blogToUpdate != null) {
            titleField.setText(blogToUpdate.getTitle());
            coverField.setText(blogToUpdate.getCover());
            authorField.setText(blogToUpdate.getAuthor());
            contentArea.setText(blogToUpdate.getContent());
        }
    }

    private void updateBlog() {
        boolean valid = true;

        if (!isValidInput(titleField.getText())) {
            showAlert("Title Error", "Title must be at least 8 characters long.");
            valid = false;
        }

        if (!isValidInput(coverField.getText())) {
            showAlert("Cover Error", "Cover URL must be at least 8 characters long.");
            valid = false;
        }

        if (!isValidInput(authorField.getText())) {
            showAlert("Author Error", "Author name must be at least 8 characters long.");
            valid = false;
        }

        if (!isValidInput(contentArea.getText())) {
            showAlert("Content Error", "Content must be at least 8 characters long.");
            valid = false;
        }

        if (!valid) {
            return; // Do not update if any field is invalid
        }

        if (blogToUpdate != null) {
            blogToUpdate.setTitle(titleField.getText());
            blogToUpdate.setCover(coverField.getText());
            blogToUpdate.setAuthor(authorField.getText());
            blogToUpdate.setContent(contentArea.getText());
            BlogDAO.updateBlog(blogToUpdate);

            if (onBlogUpdatedListener != null) {
                onBlogUpdatedListener.handle(new ActionEvent());
            }
            closeForm();
        }
    }

    private boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty() && input.trim().length() >= 8;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeForm() {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }

    public interface OnBlogUpdatedListener {
        void onBlogUpdated();
    }
}
