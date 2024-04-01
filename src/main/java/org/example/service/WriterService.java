package org.example.service;

import org.example.model.Post;
import org.example.model.Writer;
import org.example.repository.PostRepository;
import org.example.repository.WriterRepository;
import org.example.repository.impl.PostRepositoryImpl;
import org.example.repository.impl.WriterRepositoryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriterService {

    private final WriterRepository writerRepository;
    private final PostRepository postRepository;

    public WriterService() {
        this.writerRepository = new WriterRepositoryImpl();
        this.postRepository = new PostRepositoryImpl();
    }

    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    public boolean create(String body) {
        if (body.split(" ").length == 3) {
            String postsLine = body.split(" ")[2];
            List<Post> currentPosts = getPosts(postsLine);
            if (currentPosts != null) {
                List<Writer> writers = getAll();
                Writer current = writers.stream()
                        .filter(it -> it.getFirstName().equalsIgnoreCase(body.split(" ")[0]) && it.getLastName().equalsIgnoreCase(body.split(" ")[1]))
                        .findFirst()
                        .orElse(null);
                if (current == null) {
                    Writer writer = new Writer();
                    writer.setFirstName(body.split(" ")[0]);
                    writer.setLastName(body.split(" ")[1]);
                    writer.setPosts(currentPosts);
                    writerRepository.create(writer);
                    return true;
                } else {
                    writerRepository.create(current);
                    return true;
                }
            }
        }
        return false;
    }

    private List<Post> getPosts(String body) {
        List<Post> currentPosts = new ArrayList<>();
        List<Post> allPosts = postRepository.getAll();
        List<Integer> idsPosts = Arrays.stream(body.split(",")).map(Integer::valueOf).toList();
        if (!allPosts.isEmpty()) {
            allPosts.forEach(it -> {
                if (idsPosts.contains(it.getId())) {
                    currentPosts.add(it);
                }
            });
        }
        if (body.split(",").length == currentPosts.size()) {
            return currentPosts;
        }
        return null;
    }

    public void delete(Integer id) {
        writerRepository.delete(id);
    }
}
