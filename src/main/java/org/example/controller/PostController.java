package org.example.controller;

import org.example.model.Post;
import org.example.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    public List<Post> getAll() {
        return postService.getAll();
    }

    public boolean createPost(String body) {
        return postService.create(body);
    }

    public boolean updatePost(String body) {
        return postService.update(body);
    }

    public void deletePost(Integer id) {
        postService.delete(id);
    }
}
