package es.donatodev.cdi.services;

import es.donatodev.cdi.configs.*;
import es.donatodev.cdi.models.Login;
import es.donatodev.cdi.repository.LoginRepository;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;

@LoginServ
public class LoginServiceImpl implements LoginService {

    @Inject
    @LoginDao
    private LoginRepository repository;

    @Override
    public boolean checkLogin(Login login) throws SQLException {
        Login loginSql = repository.getByUsername(login.getUsername());
        if (loginSql != null) {
            return loginSql.getPassword().equals(login.getPassword());
        }
        return false;
    }

    @Override
    public List<Login> listAll() throws SQLException {
        return repository.listAll();
    }

    @Override
    public Login save(Login login) throws SQLException {
        return repository.save(login);
    }

    @Override
    public boolean delete(Login login) throws SQLException {
        return repository.delete(login);
    }
}
