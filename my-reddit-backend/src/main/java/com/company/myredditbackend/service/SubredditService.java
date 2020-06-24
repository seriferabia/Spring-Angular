package com.company.myredditbackend.service;

import com.company.myredditbackend.exceptions.SpringRedditException;
import com.company.myredditbackend.mapper.SubredditMapper;
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
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDTO save(SubredditDTO subredditDTO) {
        Subreddit subreddit = subredditMapper.mapDtoToSubreddit(subredditDTO);
        Subreddit savedSubreddit = subredditRepository.save(subreddit);
        subredditDTO.setId(savedSubreddit.getId());
        return subredditDTO;

    }


    @Transactional(readOnly = true)
    public List<SubredditDTO> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SubredditDTO getById(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() ->
                new SpringRedditException("Invalid id"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
