package org.example.service;

import org.example.model.Label;
import org.example.model.Post;
import org.example.model.PostStatus;
import org.example.repository.PostRepository;
import org.example.repository.impl.PostRepositoryImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PostService {

    private PostRepository postRepository;

    public PostService() {
        this.postRepository = new PostRepositoryImpl();
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post create(String body) {
        String content = body.split("___")[0].trim();
        List<Label> labels = postRepository.getLabels();
        List<Integer> labelIds = Arrays
                .stream(body.split("___")[1].replaceAll(" ", "").split(","))
                .map(Integer::valueOf)
                .toList();
        if (new HashSet<>(labels.stream().map(Label::getId).toList()).containsAll(labelIds)) {
            List<Label> currentLabels = labels.stream().filter(it -> labelIds.contains(it.getId())).toList();
            Post post = new Post();
            post.setContent(content);
            post.setCreated(new Timestamp(System.currentTimeMillis()));
            post.setUpdated(new Timestamp(System.currentTimeMillis()));
            post.setLabels(currentLabels);
            post.setStatus(PostStatus.ACTIVE);
            return postRepository.create(post);
        }
        return null;
    }

    public Post update(String body) {
        List<Post> allPosts = postRepository.getAll();
        int id = Integer.parseInt(body.split("___")[0]);
        String content = body.split("___")[1].trim();
        List<Label> allLabels = postRepository.getLabels();
        List<Integer> idsLabels = Arrays
                .stream(body.split("___")[2].trim().replaceAll(" ", "").split(","))
                .map(Integer::valueOf)
                .toList();
        if (new HashSet<>(allLabels.stream().map(Label::getId).toList()).containsAll(idsLabels)) {
            List<Label> currentLabels = allLabels.stream().filter(it -> idsLabels.contains(it.getId())).toList();
            Post post = allPosts.stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null);
            if (post != null) {
                post.setContent(content);
                post.setLabels(currentLabels);
                post.setUpdated(new Timestamp(System.currentTimeMillis()));
                postRepository.update(post);
            }
            return post;
        }
        return null;
    }

    public void delete(Integer id) {
        postRepository.delete(id);
    }
}
