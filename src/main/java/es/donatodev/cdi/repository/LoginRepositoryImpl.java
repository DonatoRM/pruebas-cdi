package es.donatodev.cdi.repository;

import es.donatodev.cdi.configs.*;
import es.donatodev.cdi.models.Login;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.*;

@LoginDao
public class LoginRepositoryImpl implements LoginRepository {

    @Inject
    @DBConnection
    private Connection conn;

    @Override
    public List<Login> listAll() throws SQLException {
        List<Login> logins = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pruebas.login")) {
            while (rs.next()) {
                Login login = getLogin(rs);
                logins.add(login);
            }
        }
        return logins;
    }

    @Override
    public Login getById(long id) throws SQLException {
        Login login = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pruebas.login WHERE id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    login = getLogin(rs);
                }
            }
        }
        return login;
    }

    @Override
    public Login getByUsername(String username) throws SQLException {
        Login login = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pruebas.login WHERE username=?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    login = getLogin(rs);
                }
            }
        }
        return login;
    }

    @Override
    public Login save(Login login) throws SQLException {
        Login loginSql = this.getByUsername(login.getUsername());
        if (loginSql == null || (loginSql.getId() == login.getId() && loginSql.getUsername()
                .equals(login.getUsername()))) {
            String sql;
            if (loginSql == null) {
                sql = "INSERT INTO pruebas.login(username,password) VALUES(?,?)";
            } else {
                sql = "UPDATE pruebas.login SET username=?, password=? WHERE id=?";
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, login.getUsername());
                stmt.setString(2, login.getPassword());
                if (loginSql != null) {
                    stmt.setLong(3, login.getId());
                }
                stmt.executeUpdate();
                if (loginSql == null) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            login.setId(rs.getLong("id"));
                        }
                    }
                }
            }
        }
        return login;
    }

    @Override
    public boolean delete(Login login) throws SQLException {
        boolean result = false;
        Login loginSql = this.getByUsername(login.getUsername());
        if (loginSql != null) {
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM pruebas.login WHERE id=?")) {
                stmt.setLong(1, loginSql.getId());
                stmt.executeUpdate();
                result = true;
            }
        }
        return result;
    }

    private static Login getLogin(ResultSet rs) throws SQLException {
        Login login = new Login();
        login.setId(rs.getLong("id"));
        login.setUsername(rs.getString("username"));
        login.setPassword(rs.getString("password"));
        return login;
    }
}
