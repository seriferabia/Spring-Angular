package com.company.myredditbackend.service;

import com.company.myredditbackend.exceptions.SpringRedditException;
import com.company.myredditbackend.mapper.CommentMapper;
import com.company.myredditbackend.persistence.dto.CommentDto;
import com.company.myredditbackend.persistence.model.Comment;
import com.company.myredditbackend.persistence.model.NotificationEmail;
import com.company.myredditbackend.persistence.model.Post;
import com.company.myredditbackend.persistence.model.User;
import com.company.myredditbackend.persistence.repository.CommentRepository;
import com.company.myredditbackend.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private static final String POST_URL = "";

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostService postService;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final UserRepository userRepository;

    public CommentDto save(CommentDto commentDto) {
        Post post = postService.getPostWithId(commentDto.getPostId());
        Comment comment = commentMapper.mapDtoToComment(commentDto, post, authService.getCurrentUser());
        Comment savedComment = commentRepository.save(comment);

        String message = mailContentBuilder.build(
                comment.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
        return commentMapper.mapToDto(savedComment);
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsForPost(Long postId) {
        Post post = postService.getPostWithId(postId);
        return commentRepository.findAllByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new SpringRedditException("User not Found! Invalid username : " + username));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail("Commented on your post!", user.getEmail(), message));
    }

}
