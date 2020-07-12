package com.company.myredditbackend.mapper;

import com.company.myredditbackend.persistence.dto.PostRequest;
import com.company.myredditbackend.persistence.dto.PostResponse;
import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.Subreddit;
import com.company.myredditbackend.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mappings({
            @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())"),
            @Mapping(target = "postRequest.description", source = "description"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "subreddit", source = "subreddit"),
            @Mapping(target = "user", source = "user"),

    })
    Post mapRequestToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mappings({
            @Mapping(target = "userName", source = "user.username"),
            @Mapping(target = "subredditName", source = "subreddit.name")
    })
    PostResponse mapPostToResponse(Post post);
}
