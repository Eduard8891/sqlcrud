package org.example.controller;

import org.example.model.Post;
import org.example.service.PostService;

import java.util.List;

public class PostController {
    private PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    public List<Post> getAll() {
        return postService.getAll();
    }

    public boolean createPost(String body) {
        postService.create(body);
        return true;
    }

    public boolean updatePost(String body) {
        postService.update(body);
        return true;
    }

    public void deletePost(Integer id) {
        postService.delete(id);
    }
}
