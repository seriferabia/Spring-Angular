package com.company.myredditbackend.persistence.repository;

import com.company.myredditbackend.persistence.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
}
