package com.company.myredditbackend.controller;

import com.company.myredditbackend.persistence.dto.CommentDto;
import com.company.myredditbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto responseDto = commentService.save(commentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/by-postId/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsForPost(@PathVariable Long postId) {
        List<CommentDto> comments = commentService.getCommentsForPost(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comments);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable String username) {
        List<CommentDto> comments = commentService.getCommentsByUser(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comments);
    }

}
