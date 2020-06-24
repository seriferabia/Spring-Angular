package com.company.myredditbackend.service;

import com.company.myredditbackend.persistence.dto.SubredditDTO;
import com.company.myredditbackend.persistence.model.Subreddit;
import com.company.myredditbackend.persistence.repository.SubredditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubredditService {
    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDTO save(SubredditDTO subredditDTO) {
        Subreddit subreddit = toSubreddit(subredditDTO);
        Subreddit savedSubreddit = subredditRepository.save(subreddit);
        subredditDTO.setId(savedSubreddit.getId());
        return subredditDTO;

    }

    private Subreddit toSubreddit(SubredditDTO subredditDTO) {
        return Subreddit.builder()
                .name(subredditDTO.getSubredditName())
                .description(subredditDTO.getDescription())
                .build();

    }

    @Transactional(readOnly = true)
    public List<SubredditDTO> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private SubredditDTO toDTO(Subreddit subreddit) {
        return SubredditDTO.builder()
                .id(subreddit.getId())
                .subredditName(subreddit.getName())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }
}
