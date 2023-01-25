package com.technews.javatechnewsapi.repository;

import com.technews.javatechnewsapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//annotations
@Repository// a repository is a class that fulfills
// the role of a data access object(DAO)
// -- it contains data rerieval, storage, and
// search functionality

// ensuring methods are available via inheritance through extending
// JpaRepository, thus, the interface will inherit the methods used
// to access the database for standard CRUD operations

//To ensure that the repository can take User (from the entity model we created) and the id of that user, we specify an Integer

public interface UserRepository extends JpaRepository<User, Integer> {
    // custom query method findUserByEmail()
    // This method will allow us to do what the name implies—find
    // users by their email. Naming methods in this specific way
    // allows Spring Data JPA to write the SQL for that method based
    // solely on the name.
    User findUserByEmail(String email) throws Exception;
}