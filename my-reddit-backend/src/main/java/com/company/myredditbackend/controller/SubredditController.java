package com.company.myredditbackend.controller;

import com.company.myredditbackend.persistence.dto.SubredditDto;
import com.company.myredditbackend.service.SubredditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
@Slf4j
public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDTO));
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubredditById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getById(id));
    }
}
