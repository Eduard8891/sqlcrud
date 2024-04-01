package org.example.controller;

import org.example.model.Writer;
import org.example.service.WriterService;

import java.util.List;

public class WriterController {
    private final WriterService writerService;

    public WriterController() {
        this.writerService = new WriterService();
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }

    public boolean createWriter(String body) {
        return writerService.create(body);
    }

    public void deleteWriter(Integer id) {
        writerService.delete(id);
    }
}
