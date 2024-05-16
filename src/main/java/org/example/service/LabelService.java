package org.example.service;

import org.example.model.Label;
import org.example.model.PostStatus;
import org.example.repository.LabelRepository;
import org.example.repository.impl.LabelRepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LabelService {
    private LabelRepository labelRepository;

    public LabelService() {
        this.labelRepository = new LabelRepositoryImpl();
    }

    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    public Label create(String body) {
        Label label = new Label();
        label.setName(body);
        label.setStatus(PostStatus.ACTIVE);
        labelRepository.create(label);
        return label;
    }

    public void delete(Integer id) {
        labelRepository.delete(id);
    }
}
