package org.example.controller;


import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.example.repository.impl.LabelRepositoryImpl;

import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository;

    public LabelController() {
        this.labelRepository = new LabelRepositoryImpl();
    }

    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    public boolean createLabel(String body) {
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

    public void deleteLabel(Integer id) {
        labelRepository.delete(id);
    }
}
