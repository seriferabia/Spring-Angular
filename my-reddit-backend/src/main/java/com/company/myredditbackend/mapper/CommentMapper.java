package com.company.myredditbackend.mapper;

import com.company.myredditbackend.persistence.dto.CommentDto;
import com.company.myredditbackend.persistence.model.Comment;
import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "text", source = "commentDto.text"),
            @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())"),
            @Mapping(target = "post", source = "post"),
            @Mapping(target = "user", source = "user"),

    })
    Comment mapDtoToComment(CommentDto commentDto, Post post, User user);

    @Mappings({
            @Mapping(target = "postId", expression = "java(comment.getPost().getId())"),
            @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    })
    CommentDto mapToDto(Comment comment);

}
