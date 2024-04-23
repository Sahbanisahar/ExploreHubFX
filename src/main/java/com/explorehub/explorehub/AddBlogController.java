package com.explorehub.explorehub;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    // Define a listener for blog added event
    private EventHandler<ActionEvent> onBlogAddedListener;

    // Method to set the listener
    public void setOnBlogAddedListener(EventHandler<ActionEvent> listener) {
        this.onBlogAddedListener = listener;
    }

    // Add functionality for submitting the blog post
    @FXML
    private void handleSubmitButton() {
        // You can implement the logic to handle the submission of the blog post here
        String title = titleField.getText();
        String cover = coverField.getText();
        String author = authorField.getText();
        String content = contentArea.getText();
        // Example: Save the blog post to the database
        Blog newBlog = new Blog(0, cover, title, author, content); // id is 0 since it will be assigned by the database
        BlogDAO.saveBlog(newBlog);

        // Notify the listener that a new blog has been added
        if (onBlogAddedListener != null) {
            onBlogAddedListener.handle(new ActionEvent());
        }

        // Close the add page after submission
        submitButton.getScene().getWindow().hide();
    }
}
