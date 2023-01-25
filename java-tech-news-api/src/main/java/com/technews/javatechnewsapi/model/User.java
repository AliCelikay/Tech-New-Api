package com.technews.javatechnewsapi.model;

// used at class level to mark a property or list of properties to be ignored.
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// formerly known as Java Persistence API(JPA), is a Java specification for managing relational data in Java Enterprise applications
import jakarta.persistence.*;

//a marker interface your classes must implement if they are to be serialized and deserialized
// by implementing Serializable we inform Java Virtual Machine(JVM) that this model will be serialized or converted into a bytestream in order to store entries for these models in a databse
import java.io.Serializable;

//an ordered collection
import java.util.List;

//provides static utility methods for objects which can be used to perform some of the everyday tasks like checking for equality, null checks, etc
import java.util.Objects;

//@Entity marks this as a persistable object, so that the User class can map to a table.
@Entity

// @JsonIgnoreProperties specifies properties that should be ignored when serializing
// this object to JSON. The two arguments that follow the annotation are the properties to be ignored. Note that you could add many more if necessary.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

//@Table specifies the name of the table that this class maps to. If this annotation
// isn't present, the table name will be the class name by default.
@Table(name="user")
public class User implements Serializable {
    // creating 8 instance variables and instance-level annotations at the top of select instances
    @Id //id will be used as unique identifier
    @GeneratedValue(strategy = GenerationType.AUTO) // it will be an auto generated value.

    // private: declares a member's access as private. That is, the member is only visible within the class, not from any other class (including subclasses). The visibility of private members extends to nested classes.
    private Integer id;

    private String username;

    @Column(unique=true) // signals that this value must be unique
    private String email;

    private String password;

    @Transient // signals to Spring Data JPA that this data is NOT to be persisted in the database because we don't or want a users logged-in status to persist in data
    boolean loggedIn;

    // these instance variables will be lists
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch=FetchType.EAGER)

    //creates the relationships between the tables automatically.
    // EAGER means that this list will gather all of its necessary information immediately after being created
    // you can only ever designate a single list as EAGER
    private List<Post> posts;//Connected to Post entity model file/class

    // Need to use FetchType.LAZY to resolve multiple bags exception
    // A bag is a collection where removing items is not supported—its purpose is to provide clients with the ability to collect items and then to iterate through the collected items
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch=FetchType.LAZY)

    //creates the relationships between the tables automatically.
    // LAZY means that it will only gather information as they need it
    private List<Vote> votes;//Connected to Vote entity model file/class

    // Need to use FetchType.LAZY to resolve multiple bags exception
    // A bag is a collection where removing items is not supported—its purpose is to provide clients with the ability to collect items and then to iterate through the collected items
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch=FetchType.LAZY)

    //creates the relationships between the tables automatically.
    // LAZY means that it will only gather information as they need it
    private List<Comment> comments;//Connected to Comment entity model file/class

    public User() {}
    //constructor
    public User(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return loggedIn == user.loggedIn && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(posts, user.posts) && Objects.equals(votes, user.votes) && Objects.equals(comments, user.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, loggedIn, posts, votes, comments);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                ", posts=" + posts +
                ", votes=" + votes +
                ", comments=" + comments +
                '}';
    }
}
