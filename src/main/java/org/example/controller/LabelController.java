package org.example.controller;

import org.example.model.Label;
import org.example.service.LabelService;

import java.util.List;

public class LabelController {
    private final LabelService labelService;

    public LabelController() {
        this.labelService = new LabelService();
    }

    public List<Label> getAll() {
        return labelService.getAll();
    }

    public boolean createLabel(String body) {
        return labelService.create(body);
    }

    public void deleteLabel(Integer id) {
        labelService.delete(id);
    }
}
