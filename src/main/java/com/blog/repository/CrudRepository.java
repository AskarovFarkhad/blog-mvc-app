package com.blog.repository;

import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

public interface CrudRepository<T> {

    Optional<ResultSet> getAll();

    Optional<ResultSet> getById(UUID id);

    int save(T entity);

    int update(T entity);

    int delete(UUID uuid);
}