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
            @Mapping(expression = "java(java.time.Instant.now())", target = "createdDate"),
            @Mapping(source = "postRequest.description", target = "description"),
            @Mapping(source = "postRequest.postId", target = "id"),
            @Mapping(source = "subreddit", target = "subreddit"),
            @Mapping(source = "user", target = "user"),

    })
    Post mapRequestToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mappings({
            @Mapping(target = "userName", source = "user.username"),
            @Mapping(target = "subredditName", source = "subreddit.name")
    })
    PostResponse mapPostToResponse(Post post);
}
