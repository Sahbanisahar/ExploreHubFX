package com.explorehub.explorehub;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;

public class ListBlogsController {

    @FXML
    private TextField searchField;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button addBlogButton;

    @FXML
    public void initialize() {
        loadBlogs();
        //setupSearchListener();
    }

    /* private void setupSearchListener() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchBlogs(newValue);
        });
    } */

    private void searchBlogs(String query) {
        List<Blog> searchedBlogs = BlogDAO.searchBlogsByTitle(query);
        gridPane.getChildren().clear();
        gridPane.setHgap(10);
        gridPane.setVgap(20);

        // Define grid properties
        int numColumns = 3;
        int rowIndex = 0;
        int colIndex = 0;

        // Iterate over searched blogs and populate the grid
        for (Blog blog : searchedBlogs) {
            // Create new instances of labels and buttons for each blog post
            Label titleLabel = new Label();
            titleLabel.setStyle("-fx-font-weight: bold;");
            Label contentLabel = new Label();
            Button readMoreButton = new Button("Read More");
            readMoreButton.setStyle("-fx-background-color: orange; -fx-text-fill: white;"); // Set button style
            Button updateButton = new Button("Update");
            updateButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;"); // Set button style
            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;"); // Set button style

            // Load cover image dynamically
            ImageView imageView = new ImageView();
            Image coverImage = loadCoverImage(blog.getCover());
            imageView.setImage(coverImage);
            imageView.setFitWidth(300);
            imageView.setFitHeight(200);

            // Set content for the blog post
            titleLabel.setText(blog.getTitle());
            contentLabel.setText(truncateContent(blog.getContent()));

            // Create new instance of HBox to hold buttons
            HBox buttonBox = new HBox(10); // Set spacing between buttons
            buttonBox.getChildren().addAll(readMoreButton, updateButton, deleteButton);

            // Create new instance of VBox for each blog post
            VBox newBlogBox = new VBox();
            newBlogBox.getStyleClass().add("blog");
            newBlogBox.getChildren().addAll(imageView, titleLabel, contentLabel, buttonBox);

            // Add action for the "Read More" button
            readMoreButton.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("BlogDetails.fxml"));
                    Parent root = loader.load();

                    // Get the controller and set the blog data
                    BlogDetailsController blogDetailsController = loader.getController();
                    blogDetailsController.setBlogData(blog);

                    // Set up the stage for the blog details page
                    Stage stage = new Stage();
                    stage.setTitle(blog.getTitle());
                    stage.setWidth(1095);
                    stage.setHeight(800);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            // Add action for the "Update" button
            updateButton.setOnAction(e -> {
                openUpdateForm(blog);
                BlogDAO.updateBlog(blog);
            });

            // Add action for the "Delete" button
            deleteButton.setOnAction(e -> {
                // Delete the blog from the database
                BlogDAO.deleteBlog(blog.getId());
                // Refresh the list of blogs
                searchBlogs(query);
            });

            // Add scaling effect on hover
            addScalingEffect(newBlogBox);

            // Add new VBox to the grid pane
            gridPane.add(newBlogBox, colIndex, rowIndex);

            // Update column and row indices
            colIndex++;
            if (colIndex >= numColumns) {
                colIndex = 0;
                rowIndex++;
            }
        }
    }
    // Method to load blogs from the database and populate the grid
    // Load blogs from the database and populate the grid
    private void loadBlogs() {
        // Clear the existing grid content
        gridPane.getChildren().clear();

        // Set the gap between items in the grid
        gridPane.setHgap(10);
        gridPane.setVgap(20);

        // Get list of blogs from database
        List<Blog> blogs = BlogDAO.getAllBlogs();

        // Define grid properties
        int numColumns = 3;
        int rowIndex = 0;
        int colIndex = 0;

        // Iterate over blogs in reverse order and populate the grid
        for (int i = blogs.size() - 1; i >= 0; i--) {
            Blog blog = blogs.get(i);
            // Load cover image dynamically using the URL from the database
            Image coverImage = loadCoverImage(blog.getCover());

            // Create new instances of labels and buttons for each blog post
            Label titleLabel = new Label();
            titleLabel.setStyle("-fx-font-weight: bold;");
            Label contentLabel = new Label();
            Button readMoreButton = new Button("Read More");
            readMoreButton.setStyle("-fx-background-color: orange; -fx-text-fill: white;"); // Set button style
            Button updateButton = new Button("Update");
            updateButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;"); // Set button style
            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;"); // Set button style

            // Load cover image dynamically
            ImageView imageView = new ImageView();
            imageView.setImage(coverImage);
            imageView.setFitWidth(300);
            imageView.setFitHeight(200);

            // Set content for the blog post
            titleLabel.setText(blog.getTitle());
            contentLabel.setText(truncateContent(blog.getContent()));

            // Create new instance of HBox to hold buttons
            HBox buttonBox = new HBox(10); // Set spacing between buttons
            buttonBox.getChildren().addAll(readMoreButton, updateButton, deleteButton);

            // Create new instance of VBox for each blog post
            VBox newBlogBox = new VBox();
            newBlogBox.getStyleClass().add("blog");
            newBlogBox.getChildren().addAll(imageView, titleLabel, contentLabel, buttonBox);

            // Add action for the "Read More" button
            readMoreButton.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("BlogDetails.fxml"));
                    Parent root = loader.load();

                    // Get the controller and set the blog data
                    BlogDetailsController blogDetailsController = loader.getController();
                    blogDetailsController.setBlogData(blog);

                    // Set up the stage for the blog details page
                    Stage stage = new Stage();
                    stage.setTitle(blog.getTitle());
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            // Add action for the "Update" button
            updateButton.setOnAction(e -> {
                openUpdateForm(blog);
                BlogDAO.updateBlog(blog);
            });

            // Add action for the "Delete" button
            deleteButton.setOnAction(e -> {
                // Delete the blog from the database
                BlogDAO.deleteBlog(blog.getId());
                // Refresh the list of blogs
                loadBlogs();
            });

            // Add scaling effect on hover
            addScalingEffect(newBlogBox);

            // Add new VBox to the grid pane
            gridPane.add(newBlogBox, colIndex, rowIndex);

            // Update column and row indices
            colIndex++;
            if (colIndex >= numColumns) {
                colIndex = 0;
                rowIndex++;
            }
        }
    }


    // Function to load cover image from resources

    private Image loadCoverImage(String coverUrl) {
        if (coverUrl == null || coverUrl.isEmpty()) {
            // If coverUrl is null or empty, return a default image or handle it as appropriate
            return getDefaultCoverImage(); // You need to implement this method
        } else {
            // Attempt to load the image from the provided coverUrl
            try (InputStream inputStream = getClass().getResourceAsStream(coverUrl)) {
                if (inputStream != null) {
                    return new Image(inputStream);
                } else {
                    // If inputStream is null, the resource couldn't be loaded, handle it as appropriate
                    return getDefaultCoverImage(); // You need to implement this method
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception as appropriate, e.g., return a default image
                return getDefaultCoverImage();
            }
        }
    }

    private Image getDefaultCoverImage() {
        InputStream defaultInputStream = getClass().getResourceAsStream("/photos/cover.png");
        if (defaultInputStream != null) {
            return new Image(defaultInputStream);
        } else {
            // Handle case where default image cannot be loaded
            return null;
        }
    }


    // Function to truncate content to first three words followed by "..."
    private String truncateContent(String content) {
        StringTokenizer tokenizer = new StringTokenizer(content);
        StringBuilder truncatedContent = new StringBuilder();
        int wordCount = 0;
        while (tokenizer.hasMoreTokens() && wordCount < 3) {
            truncatedContent.append(tokenizer.nextToken()).append(" ");
            wordCount++;
        }
        if (tokenizer.hasMoreTokens()) {
            truncatedContent.append("...");
        }
        return truncatedContent.toString().trim();
    }

    // Function to add scaling effect on hover
    private void addScalingEffect(VBox node) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), node);
        scaleIn.setToX(1.05);
        scaleIn.setToY(1.05);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), node);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        node.setOnMouseEntered(event -> scaleIn.play());
        node.setOnMouseExited(event -> scaleOut.play());
    }

    // Action for the "Add a Post" button
    @FXML
    private void handleAddPostButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddBlog.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Blog");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            AddBlogController addBlogController = (AddBlogController) loader.getController();
            addBlogController.setOnBlogAddedListener((event) -> {
                // Clear the grid pane's children before reloading blogs
                gridPane.getChildren().clear();
                loadBlogs();
                stage.close();
            });
            stage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }


    // Function to open the update form for a blog
    private void openUpdateForm(Blog blog) {
        try {
            // Load the FXML file for the update page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateBlog.fxml"));
            Parent root = loader.load();

            // Set up the stage for the update page
            Stage stage = new Stage();
            stage.setTitle("Update Blog");
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Pass the selected blog to the controller of the update page
            UpdateBlogController updateBlogController = loader.getController();
            updateBlogController.setBlogToUpdate(blog);
            updateBlogController.setOnBlogUpdatedListener(event -> {
                loadBlogs(); // Refresh blogs
            });
            // Show the update form
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
