package com.company.myredditbackend.persistence.repository;

import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.User;
import com.company.myredditbackend.persistence.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByIdDesc(Post post, User currentUser);
}
