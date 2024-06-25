package es.donatodev.cdi.services;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    List<T> listAll() throws SQLException;

    T save(T t) throws SQLException;

    boolean delete(T t) throws SQLException;
}
