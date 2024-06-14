package com.blog.service.impl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.execption.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper modelMapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found by id number =" + postId));

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        CommentDto dto = mapToDTO(newComment);

        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {


        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found by id; " + postId)
        );

        List<Comment> comments = commentRepository.findByPostId(postId);
         return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(Long postId, Long CommentId) {
        postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post not found for the id :" +postId)
        );
        Comment comment = commentRepository.findById(CommentId).orElseThrow(
                () -> new RuntimeException("Comment not found with id:" + CommentId)
        );
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post not found for the id :" +postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new RuntimeException("Comment not found with id:" + commentId)
        );
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return  mapToDTO(updatedComment);


    }

    @Override
    public void deleteComment(long postId, long commentId) {
        postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post not found for the id :" +postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new RuntimeException("Comment not found with id:" + commentId)
        );
        commentRepository.deleteById(commentId);

    }

    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }

}
