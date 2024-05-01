package com.explorehub.explorehub;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddBlogController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField coverField;

    @FXML
    private TextField authorField;

    @FXML
    private TextArea contentArea;

    @FXML
    private Button submitButton;

    private EventHandler<ActionEvent> onBlogAddedListener;

    public void setOnBlogAddedListener(EventHandler<ActionEvent> listener) {
        this.onBlogAddedListener = listener;
    }

    @FXML
    private void handleSubmitButton() {
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
            return;
        }

        Blog newBlog = new Blog(0, coverField.getText(), titleField.getText(), authorField.getText(), contentArea.getText());
        BlogDAO.saveBlog(newBlog);

        if (onBlogAddedListener != null) {
            onBlogAddedListener.handle(new ActionEvent());
        }

        submitButton.getScene().getWindow().hide();
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
}
