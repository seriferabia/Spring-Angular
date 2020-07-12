package com.company.myredditbackend.mapper;

import com.company.myredditbackend.persistence.dto.VoteDto;
import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.User;
import com.company.myredditbackend.persistence.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VoteMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "voteType", source = "voteDto.voteType"),
            @Mapping(target = "post", source = "post"),
            @Mapping(target = "user", source = "user"),

    })
    Vote mapDtoToVote(VoteDto voteDto, Post post, User user);
}
