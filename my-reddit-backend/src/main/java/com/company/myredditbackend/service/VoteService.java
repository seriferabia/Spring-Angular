package com.company.myredditbackend.service;

import com.company.myredditbackend.exceptions.SpringRedditException;
import com.company.myredditbackend.mapper.VoteMapper;
import com.company.myredditbackend.persistence.dto.VoteDto;
import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.Vote;
import com.company.myredditbackend.persistence.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.company.myredditbackend.persistence.model.VoteType.UPVOTE;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostService postService;
    private final AuthService authService;
    private final VoteMapper voteMapper;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postService.getPostWithId(voteDto.getPostId());

        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByIdDesc(post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post!");
        }

        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCounter(post.getVoteCounter() + 1);
        } else {
            post.setVoteCounter(post.getVoteCounter() - 1);
        }

        Vote vote = voteMapper.mapDtoToVote(voteDto, post, authService.getCurrentUser());
        voteRepository.save(vote);
    }
}
