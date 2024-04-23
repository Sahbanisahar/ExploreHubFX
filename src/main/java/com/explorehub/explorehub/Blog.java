package com.explorehub.explorehub;
import java.util.Objects;

public class Blog {
    private int id;
    private String cover;
    private String title;
    private String author;
    private String content;

    // Constructors
    public Blog() {
    }

    public Blog(int id, String cover, String title, String author, String content) {
        this.id = id;
        this.cover = cover;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Override equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return id == blog.id &&
                Objects.equals(cover, blog.cover) &&
                Objects.equals(title, blog.title) &&
                Objects.equals(author, blog.author) &&
                Objects.equals(content, blog.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cover, title, author, content);
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}