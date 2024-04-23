package com.explorehub.explorehub;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    public void setBlogToUpdate(Blog blog) {
        this.blogToUpdate = blog;
        fillFieldsWithBlogData();
    }

    @FXML
    private void initialize() {
        updateButton.setOnAction(event -> updateBlog());
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
        if (blogToUpdate != null) {
            // Update the blog data
            blogToUpdate.setTitle(titleField.getText());
            blogToUpdate.setCover(coverField.getText());
            blogToUpdate.setAuthor(authorField.getText());
            blogToUpdate.setContent(contentArea.getText());
            // Save the updated blog data to the database (You need to implement this)
            // BlogDAO.updateBlog(blogToUpdate);
            // Close the update form
            closeForm();
        }
    }

    private void closeForm() {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }
}
