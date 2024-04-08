package org.example;

import org.example.model.Label;
import org.example.model.PostStatus;
import org.example.repository.LabelRepository;
import org.example.service.LabelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LabelServiceTest {

    @Mock
    private static LabelRepository labelRepository;

    @InjectMocks
    private static LabelService labelService;

    @Test
    public void getAllTest() {
        List<Label> labels = List.of(new Label(), new Label(), new Label());
        Mockito.when(labelRepository.getAll()).thenReturn(labels);
        Assertions.assertEquals(labels, labelService.getAll());
    }

    @Test
    public void createLabelTest() {
        Label label = new Label();
        label.setName("testLabel");
        Mockito.when(labelRepository.create(label)).thenReturn(label);
        Assertions.assertEquals(labelService.create("testLabel").getName(), label.getName());
    }
}
