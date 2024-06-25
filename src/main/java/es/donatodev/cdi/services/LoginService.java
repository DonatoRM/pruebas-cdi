package es.donatodev.cdi.services;

import es.donatodev.cdi.models.Login;

import java.sql.SQLException;

public interface LoginService extends Service<Login> {
    boolean checkLogin(Login login) throws SQLException;
}
