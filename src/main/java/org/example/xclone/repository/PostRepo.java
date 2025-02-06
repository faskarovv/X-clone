package org.example.xclone.repository;

import org.example.xclone.model.entity.Post;
import org.example.xclone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
   Post findByName(String name);

   List<Post> findByUser(User user);


}
