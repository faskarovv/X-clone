package org.example.xclone.controller;


import jakarta.validation.Valid;
import org.example.xclone.model.dto.PostDto;
import org.example.xclone.model.entity.Post;
import org.example.xclone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PostDto.response>> getAll(){
        List<PostDto.response> responseList = postService.getAllPosts();

        return ResponseEntity.ok().body(responseList);
    }


    @GetMapping("/getBy/{id}")
    public ResponseEntity<PostDto.response> getById(@PathVariable  Long id){
        PostDto.response response = postService.getById(id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostDto.response> createPost(@Valid @RequestBody PostDto.createRequest post){
        PostDto.response response = postService.createNewPost(post);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/updatePost/{name}")
    public ResponseEntity<PostDto.response> updatePost(
            @Valid @RequestBody PostDto.updateRequest post,
               @PathVariable String name){

        PostDto.response response = postService.updatePost(post, name);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }



}
