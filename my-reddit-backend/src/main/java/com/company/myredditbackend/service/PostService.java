package com.company.myredditbackend.service;

import com.company.myredditbackend.exceptions.SpringRedditException;
import com.company.myredditbackend.mapper.PostMapper;
import com.company.myredditbackend.persistence.dto.PostRequest;
import com.company.myredditbackend.persistence.dto.PostResponse;
import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.Subreddit;
import com.company.myredditbackend.persistence.model.User;
import com.company.myredditbackend.persistence.repository.PostRepository;
import com.company.myredditbackend.persistence.repository.SubredditRepository;
import com.company.myredditbackend.persistence.repository.UserRepository;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;


    public PostResponse save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SpringRedditException("Invalid name!"));

        Post post = postMapper.mapRequestToPost(postRequest, subreddit, authService.getCurrentUser());

        return postMapper.mapPostToResponse(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapPostToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("Invalid id"));
        return postMapper.mapPostToResponse(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId){
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SpringRedditException("Invalid subreddit id!"));

        return postRepository.findAllBySubreddit(subreddit)
                .stream()
                .map(postMapper::mapPostToResponse)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringRedditException("Invalid username " + username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapPostToResponse)
                .collect(Collectors.toList());
    }
}
