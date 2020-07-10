package com.company.myredditbackend.controller;

import com.company.myredditbackend.persistence.dto.PostRequest;
import com.company.myredditbackend.persistence.dto.PostResponse;
import com.company.myredditbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest){

        return status(HttpStatus.CREATED)
                .body(postService.save(postRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return status(HttpStatus.OK)
                .body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        return status(HttpStatus.OK)
                .body(postService.getPost(id));
    }

    @GetMapping("/by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id){
        return status(HttpStatus.OK)
                .body(postService.getPostsBySubreddit(id));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable String username){
        return status(HttpStatus.OK)
                .body(postService.getPostsByUsername(username));
    }
}
