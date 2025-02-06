package org.example.xclone.service;


import org.example.xclone.model.dto.CommentDto;
import org.example.xclone.model.entity.Comment;
import org.example.xclone.repository.CommentRepo;
import org.example.xclone.repository.UserRepo;
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




}
