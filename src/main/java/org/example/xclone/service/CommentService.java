package org.example.xclone.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.xclone.exception.EntityAlreadyInUse;
import org.example.xclone.model.dto.CommentDto;
import org.example.xclone.model.entity.Comment;
import org.example.xclone.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {


    CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;

    }

    public List<CommentDto.response> getAll() {
        List<Comment> responseList = commentRepo.findAll();

        return responseList.stream()
                .map(comment->mapToCommentDto(comment))
                .collect(Collectors.toList());
    }

    private CommentDto.response mapToCommentDto(Comment comment) {
         CommentDto.response response = new CommentDto.response(
                comment.getCommentId(),
                 comment.getContent()
        );

         return response;
    }


    public CommentDto.response getByid(Long id) {
        Comment comment = commentRepo.findById(id).orElse(null);

        return new CommentDto.response(
                comment.getCommentId(),
                comment.getContent()
        );
    }


    public CommentDto.response createNewComment(CommentDto.createRequest createRequest) {
            Comment existingComment = commentRepo.findByContent(createRequest.getContent());

            if(existingComment==null){
                throw new EntityAlreadyInUse("such entity already exist");
            }

            Comment newComment = new Comment();
            newComment.setContent(createRequest.getContent());

            Comment savedComment = commentRepo.save(newComment);

            return new CommentDto.response(
                    savedComment.getCommentId(),
                    savedComment.getContent()
            );
    }


    public CommentDto.response updateComment(CommentDto.updateRequest request, Long id) {
        Comment existingComment = commentRepo.findById(id).orElse(null);

        if(existingComment==null){
            throw new EntityNotFoundException("no such entity exisit with given id");
        }
        Comment newComment = new Comment();
        newComment.setContent(request.getContent());

        Comment updatedComment = commentRepo.save(newComment);

        return new CommentDto.response(
                updatedComment.getCommentId(),
                updatedComment.getContent()
        );
    }

    public void deleteComment(Long id) {
        Comment existingComment = commentRepo.findById(id).orElse(null);

        if(existingComment==null){
            throw new EntityNotFoundException("no such entity exist with given id");
        }
        else{
            commentRepo.delete(existingComment);
        }
    }
}
