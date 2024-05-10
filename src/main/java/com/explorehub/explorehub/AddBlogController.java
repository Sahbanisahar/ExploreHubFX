package com.explorehub.explorehub;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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

    @FXML
    private Button uploadButton; // Add upload button

    private File coverFile; // File to store the uploaded cover image

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
        if (coverFile == null) {
            showAlert("Cover Error", "Please upload a cover image.");
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

        String coverFilePath = saveCoverImage(coverFile);

        Blog newBlog = new Blog(0, coverFilePath, titleField.getText(), authorField.getText(), contentArea.getText());
        BlogDAO.saveBlog(newBlog);



        if (onBlogAddedListener != null) {
            onBlogAddedListener.handle(new ActionEvent());
        }

        submitButton.getScene().getWindow().hide();
    }

    private boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty() && input.trim().length() >= 8;
    }

    @FXML
    private void handleUploadButton() {
        // Open a file chooser dialog for the user to select the cover image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Cover Image");
        coverFile = fileChooser.showOpenDialog(submitButton.getScene().getWindow());

        // Display the selected cover image in the coverField (optional)
        if (coverFile != null) {
            coverField.setText(coverFile.getAbsolutePath());
        }
    }

    private String saveCoverImage(File file) {
        // Define the directory where you want to save the cover images
        String uploadDirectory = "src/main/resources/photos/";

        // Create the directory if it doesn't exist
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            try {
                directory.mkdirs();
            } catch (SecurityException e) {
                System.err.println("Failed to create directory: " + e.getMessage());
                return null; // Return null if directory creation fails
            }
        }

        try {
            // Generate a random file name for the cover image
            String fileName = generateRandomFileName(file.getName());
            String destinationPath = uploadDirectory + fileName;

            // Copy the uploaded file to the destination directory
            Files.copy(file.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Return the relative path to the saved cover image
            return "/photos/" + fileName;
        } catch (IOException e) {
            System.err.println("Failed to save cover image: " + e.getMessage());
            return null; // Return null in case of error
        }
    }
    private String generateRandomFileName(String originalFileName) {
        // Implement your logic to generate a random file name
        // For simplicity, you can use UUID or timestamp-based names
        return originalFileName; // Replace this with your actual implementation
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
