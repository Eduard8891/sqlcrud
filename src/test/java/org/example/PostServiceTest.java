package org.example;

import org.example.model.Label;
import org.example.model.Post;
import org.example.model.PostStatus;
import org.example.repository.LabelRepository;
import org.example.repository.PostRepository;
import org.example.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.quality.Strictness;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PostServiceTest {

    @Mock
    private static PostRepository postRepository;
    @Mock
    private static LabelRepository labelRepository;

    @InjectMocks
    private static PostService postService;

    @Test
    public void getAllTest() {
        List<Post> posts = List.of(new Post(), new Post(), new Post());
        Mockito.when(postRepository.getAll()).thenReturn(posts);
        Assertions.assertEquals(posts, postService.getAll());
    }

    @Test
    public void createPostTest() {
        Label label = new Label(5, "label", PostStatus.ACTIVE);
        Post post = new Post();
        Mockito.when(labelRepository.getAll()).thenReturn(List.of(label));
        Mockito.when(postRepository.create(any(Post.class))).thenReturn(post);
        Post current = postService.create("testContent___5");
        Assertions.assertEquals(current.getContent(), post.getContent());
    }

    @Test
    public void updatePostTest() {
        Label label = new Label(5, "label", PostStatus.ACTIVE);
        Post post = new Post();
        post.setId(8);
        post.setContent("testContent");
        Mockito.when(labelRepository.getAll()).thenReturn(List.of(label));
        Mockito.when(postRepository.getAll()).thenReturn(List.of(post));
        Mockito.when(postRepository.update(any(Post.class))).thenReturn(post);
        Post current = postService.update("8___testContent___5");
        Assertions.assertEquals(current.getContent(), post.getContent());
    }

    @Test
    public void deletePostTest() {
        Mockito.doNothing().when(postRepository).delete(10);
        postService.delete(10);
        Mockito.verify(postRepository).delete(10);
    }
}
