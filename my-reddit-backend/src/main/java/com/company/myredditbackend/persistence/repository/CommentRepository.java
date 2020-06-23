package com.company.myredditbackend.persistence.repository;

import com.company.myredditbackend.persistence.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
