package com.blog.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CrudRepository<T> {

    ResultSet getAll() throws SQLException;

    ResultSet getById(int id) throws SQLException;

    int save(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(T entity) throws SQLException;

    int deleteById(int id) throws SQLException;

    int deleteAll() throws SQLException;
}