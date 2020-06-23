package com.company.myredditbackend.persistence.repository;

import com.company.myredditbackend.persistence.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
