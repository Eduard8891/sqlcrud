package org.example;

import org.example.model.Label;
import org.example.model.Post;
import org.example.model.PostStatus;
import org.example.model.Writer;
import org.example.repository.PostRepository;
import org.example.repository.WriterRepository;
import org.example.service.WriterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WriterServiceTest {

    @Mock
    private static WriterRepository writerRepository;
    @Mock
    private static PostRepository postRepository;

    @InjectMocks
    private static WriterService writerService;

    @Test
    public void getAllTest() {
        List<Writer> writers = List.of(new Writer(), new Writer());
        Mockito.when(writerRepository.getAll()).thenReturn(writers);
        Assertions.assertEquals(writers, writerService.getAll());
    }

    @Test
    public void createPostTest() {
        Post post = new Post(15, "testContent",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                List.of(new Label(1, "label", PostStatus.ACTIVE)),
                PostStatus.ACTIVE);
        Writer writer = new Writer(1, "testName", "testLastName", List.of(post), PostStatus.ACTIVE);
        Mockito.when(postRepository.getAll()).thenReturn(List.of(post));
        Mockito.when(writerRepository.getAll()).thenReturn(List.of(writer));
        Mockito.when(writerRepository.create(writer)).thenReturn(writer);
        Assertions.assertEquals(writerService.create("testName testLastName 15"), writer);
    }


    @Test
    public void deletePostTest() {
        Mockito.doNothing().when(writerRepository).delete(10);
        writerService.delete(10);
        Mockito.verify(writerRepository).delete(10);
    }

}
