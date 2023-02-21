package com.blog.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public interface CrudRepository<T> {

    Optional<ResultSet> getAll() throws SQLException;

    Optional<ResultSet> getById(UUID id) throws SQLException;

    int save(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(UUID uuid) throws SQLException;
}