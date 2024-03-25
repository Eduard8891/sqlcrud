package org.example.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T get(ID id);

    void delete(ID id);

    List<T> getAll();

    T update(T t);

    T create(T t);
}
