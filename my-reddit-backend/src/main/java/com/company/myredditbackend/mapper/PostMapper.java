package com.company.myredditbackend.mapper;

import com.company.myredditbackend.persistence.dto.PostRequest;
import com.company.myredditbackend.persistence.dto.PostResponse;
import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.Subreddit;
import com.company.myredditbackend.persistence.model.User;
import com.company.myredditbackend.persistence.repository.CommentRepository;
import com.company.myredditbackend.persistence.repository.VoteRepository;
import com.company.myredditbackend.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    AuthService authService;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())"),
            @Mapping(target = "description", source = "postRequest.description"),
            @Mapping(target = "subreddit", source = "subreddit"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "voteCounter", constant = "0")

    })
    public abstract Post mapRequestToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mappings({
            @Mapping(target = "userName", source = "user.username"),
            @Mapping(target = "subredditName", source = "subreddit.name"),
            @Mapping(target = "commentCounter", expression = "java(commentCount(post))"),
            @Mapping(target = "duration", expression = "java(getDuration(post))")
    })
    public abstract PostResponse mapPostToResponse(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findAllByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
}
