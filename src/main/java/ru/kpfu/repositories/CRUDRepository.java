package ru.kpfu.repositories;

import java.util.List;

public interface CRUDRepository<T, ID> {
    void save(T type);
    boolean delete(ID id);
    List<T> findAll();
    boolean update(T type);
}

