package com.technews.javatechnewsapi.model;

//used at class level to mark a property or list of properties to be ignored.
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//formerly known as Java Persistence API, is a Java specification for managing relational data in Java Enterprise applications
import jakarta.persistence.*;

//a marker interface your classes must implement if they are to be serialized and deserialized
import java.io.Serializable;

//provides static utility methods for objects which can be used to perform some of the everyday tasks like checking for equality, null checks, etc
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String commentText;
    private Integer userId;
    private Integer postId;

    public Comment() {
    }

    public Comment(Integer id, String commentText, Integer userId, Integer postId) {
        this.id = id;
        this.commentText = commentText;
        this.userId = userId;
        this.postId = postId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(commentText, comment.commentText) && Objects.equals(userId, comment.userId) && Objects.equals(postId, comment.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentText, userId, postId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}