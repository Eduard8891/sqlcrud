package org.example.repository;


import org.example.model.Writer;

import java.util.List;

public interface WriterRepository extends GenericRepository<Writer, Integer> {
    @Override
    Writer get(Integer integer);

    @Override
    void delete(Integer integer);

    @Override
    List<Writer> getAll();

    @Override
    Writer update(Writer writer);

    @Override
    Writer create(Writer writer);
}
