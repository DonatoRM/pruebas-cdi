package es.donatodev.cdi.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> listAll() throws SQLException;

    T getById(long id) throws SQLException;

    T save(T t) throws SQLException;

    boolean delete(T t) throws SQLException;
}
