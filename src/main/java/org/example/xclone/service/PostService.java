package org.example.xclone.service;

import org.example.xclone.exception.EntityAlreadyInUse;
import org.example.xclone.exception.EntityNotFoundException;
import org.example.xclone.model.dto.PostDto;
import org.example.xclone.model.entity.Post;
import org.example.xclone.repository.PostRepo;
import org.example.xclone.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final UserRepo userRepo;
    PostRepo postRepo;

    @Autowired
    public PostService(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public List<PostDto.response> getAllPosts() {
        List<Post> posts = postRepo.findAll();

        return posts.stream()
                .map(post -> mapToPostDto(post))
                .collect(Collectors.toList());
    }
    private PostDto.response mapToPostDto(Post post) {
        return new PostDto.response(
               post.getPostId(),
               post.getName(),
               post.getContent()
        );
    }


    public PostDto.response getById(Long id) {
        Post post = postRepo.findById(id).orElse(null);

        if(post == null){
            throw new EntityNotFoundException("No such post exist with same ID");
        }
        else {
            PostDto.response response = new PostDto.response();
            response.setPostId(post.getPostId());
            response.setName(post.getName());
            response.setContent(post.getContent());
            return response;
        }

    }

    public PostDto.response createNewPost(PostDto.createRequest post) {
        Post exisitingPost = postRepo.findByName(post.getName());

        if(exisitingPost!=null){
            throw new EntityAlreadyInUse(" such post already exists");
        }
        Post newPost = new Post();
        newPost.setName(post.getName());
        newPost.setContent(post.getContent());

        Post savedPost = postRepo.save(newPost);

        PostDto.response response = new PostDto.response();
        response.setContent(savedPost.getContent());
        response.setName(savedPost.getName());
        response.setPostId(savedPost.getPostId());

        return response;
    }

    public PostDto.response updatePost(PostDto.updateRequest post , String name) {

        Post exisitingPost = postRepo.findByName(name);

        if(exisitingPost == null){
            throw new EntityNotFoundException("no such entity exists with same name");
        }

        Post newPost = new Post();
        newPost.setName(post.getName());
        newPost.setContent(post.getContent());

        Post updatedPost = postRepo.save(newPost);

        PostDto.response response = new PostDto.response();
        response.setPostId(updatedPost.getPostId());
        response.setName(updatedPost.getName());
        response.setContent(updatedPost.getContent());

        return response;
    }

}
