package es.donatodev.cdi.controllers;

import es.donatodev.cdi.configs.LoginServ;
import es.donatodev.cdi.exceptions.ServiceException;
import es.donatodev.cdi.models.Login;
import es.donatodev.cdi.services.LoginService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet({"/index.html", "/views/login"})
public class LoginServlet extends HttpServlet {

    @Inject
    @LoginServ
    private LoginService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null) {
            errors.put("username", "El usuario no puede ser nulo");
        } else {
            username = username.trim();
            if (username.isBlank()) {
                errors.put("username", "El usuario no puede ser vacío");
            }
        }
        if (password == null) {
            errors.put("password", "La contraseña no puede ser nula");
        } else {
            password = password.trim();
            if (password.isBlank()) {
                errors.put("password", "La contraseña no puede estar vacía");
            }
        }
        if (errors.isEmpty()) {
            Login login = new Login();
            login.setUsername(username);
            login.setPassword(password);
            try {
                if (!service.checkLogin(login)) {
                    errors.put("valid", "Usuario no válido");
                    req.setAttribute("errors", errors);
                    getServletContext().getRequestDispatcher("/views/login.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/views/fin");
                }
            } catch (SQLException e) {
                throw new ServiceException(e.getMessage(),e.getCause());
            }
        } else {
            req.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
