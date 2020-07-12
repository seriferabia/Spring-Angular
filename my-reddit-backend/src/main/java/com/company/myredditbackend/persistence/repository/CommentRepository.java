package com.company.myredditbackend.persistence.repository;

import com.company.myredditbackend.persistence.model.Comment;
import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByUser(User user);
}
