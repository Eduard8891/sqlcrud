package org.example.repository;


import org.example.model.Label;

import java.util.List;

public interface LabelRepository extends GenericRepository<Label, Integer> {
    @Override
    Label get(Integer integer);

    @Override
    void delete(Integer integer);

    @Override
    List<Label> getAll();

    @Override
    Label update(Label label);

    @Override
    Label create(Label label);
}
