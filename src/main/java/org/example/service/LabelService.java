package org.example.service;

import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.example.repository.impl.LabelRepositoryImpl;

import java.util.List;

public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService() {
        this.labelRepository = new LabelRepositoryImpl();
    }

    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    public boolean create(String body) {
        if (body.split(" ").length == 1) {
            List<Label> labels = getAll();
            Label current = labels.stream().filter(it -> it.getName().equals(body)).findFirst().orElse(null);
            if (current == null) {
                Label label = new Label();
                label.setName(body);
                labelRepository.create(label);
                return true;
            }
        }
        return false;
    }

    public void delete(Integer id) {
        labelRepository.delete(id);
    }
}
