package ru.kpfu.repositories;

public interface CRUDRepository<T, ID> {
    void save(String username, String email, String password);
}

