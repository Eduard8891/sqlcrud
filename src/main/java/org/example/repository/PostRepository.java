package org.example.repository;


import org.example.model.Label;
import org.example.model.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Integer> {

    @Override
    Post get(Integer integer);

    @Override
    void delete(Integer integer);

    @Override
    List<Post> getAll();

    @Override
    Post update(Post post);

    @Override
    Post create(Post post);
}
