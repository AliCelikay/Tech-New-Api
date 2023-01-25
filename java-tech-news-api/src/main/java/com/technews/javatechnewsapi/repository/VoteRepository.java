package com.technews.javatechnewsapi.repository;

import com.technews.javatechnewsapi.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT count(*) FROM Vote v WHERE v.postId = :id")

        //method
        //two arguments—one is a method-level annotation of @Param("id), to use the id as a parameter, and the other is the Integer id
    int countVotesByPostId(@Param("id") Integer id);
}