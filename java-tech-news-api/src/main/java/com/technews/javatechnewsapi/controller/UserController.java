package com.technews.javatechnewsapi.controller;

import com.technews.javatechnewsapi.model.Post;
import com.technews.javatechnewsapi.model.User;
import com.technews.javatechnewsapi.repository.UserRepository;
import com.technews.javatechnewsapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired //annotation tells Spring to scan the project for objects that will need to be instantiated for a class or method to run. Unlike the new operator, which instantiates all objects before they're necessarily needed, @Autowired informs Spring to only instantiate each object as needed by the program. Then it can grab and inject the proper dependencies without having to manually wire anything in the XML. This form of dependency injection improves efficiency and keeps the program light.
    UserRepository repository;

    @Autowired
    VoteRepository voteRepository;

    // The @GetMapping("/api/users") annotation on the getAllUsers() method combines the route ("/api/users") and the type of HTTP method used (GET), providing the method with a unique endpoint
    @GetMapping("/api/users")
    // methods w/o void keyword must return something
    public List<User> getAllUsers() {
        //we want getAllUsers method to return a list of users
        List<User> userList = repository.findAll();//grabing a list of users and assigning it to the userList variable
        for (User u : userList) {
            // calling the getPost function for every User, assigned to variable u inside userList [Function needs PostController]
            List<Post> postList = u.getPosts();
            //iterating over each post, invoking setVoteCount method, passing countVotesByPostId() method and finally using getId function to obtain id of posts
            for (Post p : postList) {
                p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
            }
        }
        return userList;
    }

    //instead of returning a list, getUserById function returns a single user
    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        User returnUser = repository.getById(id);

        List<Post> postList = returnUser.getPosts();
        for (Post p : postList) {
            p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
        }
        return returnUser;
    }

    // addUser method annotated with PostMapping function will allow us to add a user to the database.
    @PostMapping("/api/users")
    //inside addUser method passes @RequestBody annotation which will map the body of this request to a transfer object, then deserialize the body onto a Java object for easier use
    public User addUser(@RequestBody User user) {
        // Encrypt password
        // setPassword method offers protection, using BCrypt to encrypt the passwords for new users
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        // after encryption, we save new user and return new user
        repository.save(user);
        return user;
    }

    //update user on id, @PathVariable allows us to ent the int id in the URL as a parameter
    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User tempUser = repository.getById(id);
        if (!tempUser.equals(null)) {
            user.setId(tempUser.getId());
            repository.save(user);
        }
        return user;
    }

    //using @PathVariable to pass int to requested URL, and w/ the id we delete
    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }
}