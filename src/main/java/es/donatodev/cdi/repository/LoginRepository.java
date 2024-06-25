package es.donatodev.cdi.repository;

import es.donatodev.cdi.models.Login;

import java.sql.SQLException;

public interface LoginRepository extends Repository<Login> {
    Login getByUsername(String username) throws SQLException;
}
