package org.example.xclone.controller;


import jakarta.validation.Valid;
import org.example.xclone.model.dto.CommentDto;
import org.example.xclone.model.entity.Comment;
import org.example.xclone.service.CommentService;
import org.example.xclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/getBy/{id}")
    public ResponseEntity<CommentDto.response> getByID(@PathVariable Long id){
        CommentDto.response response = commentService.getByid(id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDto.response> createNewComment(
            @Valid @RequestBody CommentDto.createRequest createRequest){

        CommentDto.response response = commentService.createNewComment(createRequest);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDto.response> update (
            @Valid @RequestBody CommentDto.updateRequest request ,
            @PathVariable Long id){
        CommentDto.response response = commentService.updateComment(request , id);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
