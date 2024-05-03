package com.explorehub.explorehub;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {
    // Define SQL queries
    private static final String SELECT_ALL_BLOGS_SQL = "SELECT * FROM Blog";
    private static final String DELETE_BLOG_SQL = "DELETE FROM Blog WHERE id = ?";
    private static final String SEARCH_BLOGS_BY_TITLE_SQL = "SELECT * FROM Blog WHERE title LIKE ?";

    // Method to retrieve all blogs from the database
    public static List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BLOGS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getInt("id"));
                blog.setCover(resultSet.getString("cover"));
                blog.setTitle(resultSet.getString("title"));
                blog.setAuthor(resultSet.getString("author"));
                blog.setContent(resultSet.getString("content"));

                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    // Method to save a new blog to the database
    public static void saveBlog(Blog blog) {
        String INSERT_BLOG_SQL = "INSERT INTO blog (cover, title, author, content) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BLOG_SQL)) {
            preparedStatement.setString(1, blog.getCover());
            preparedStatement.setString(2, blog.getTitle());
            preparedStatement.setString(3, blog.getAuthor());
            preparedStatement.setString(4, blog.getContent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Method to delete a blog from the database
    public static void deleteBlog(int blogId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BLOG_SQL)) {
            preparedStatement.setInt(1, blogId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Method to update a blog in the database
    public static void updateBlog(Blog blog) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establish connection
            connection = DatabaseConnection.getConnection();

            // Prepare SQL statement
            String sql = "UPDATE blog SET cover = ?, title = ?, author = ?, content = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, blog.getCover());
            statement.setString(2, blog.getTitle());
            statement.setString(3, blog.getAuthor());
            statement.setString(4, blog.getContent());
            statement.setInt(5, blog.getId());

            // Execute update
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Method to search blogs by title
    public static List<Blog> searchBlogsByTitle(String query) {
        List<Blog> searchedBlogs = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BLOGS_BY_TITLE_SQL)) {
            preparedStatement.setString(1, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = extractBlogFromResultSet(resultSet);
                searchedBlogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchedBlogs;
    }
    private static Blog extractBlogFromResultSet(ResultSet resultSet) throws SQLException {
        Blog blog = new Blog();
        blog.setId(resultSet.getInt("id"));
        blog.setCover(resultSet.getString("cover"));
        blog.setTitle(resultSet.getString("title"));
        blog.setAuthor(resultSet.getString("author"));
        blog.setContent(resultSet.getString("content"));
        return blog;
    }
}