package com.company.myredditbackend.persistence.repository;


import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.Subreddit;
import com.company.myredditbackend.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);

}
