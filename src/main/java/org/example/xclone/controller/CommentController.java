package org.example.xclone.controller;


import org.example.xclone.model.dto.CommentDto;
import org.example.xclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {


    CommentService commentService;


    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CommentDto.response>> getAll(){
        List<CommentDto.response> commentList = commentService.getAll();

        return ResponseEntity.ok().body(commentList);
    }
}
