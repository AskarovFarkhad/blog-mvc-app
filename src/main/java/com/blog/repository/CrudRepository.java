package com.blog.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public interface CrudRepository<T> {

    ResultSet getAll() throws SQLException;

    ResultSet getById(UUID id) throws SQLException;

    int save(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(UUID uuid) throws SQLException;

    int deleteById(int id) throws SQLException;

    int deleteAll() throws SQLException;
}