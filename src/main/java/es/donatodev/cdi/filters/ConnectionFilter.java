package es.donatodev.cdi.filters;

import es.donatodev.cdi.configs.DBConnection;
import es.donatodev.cdi.exceptions.ServiceException;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

    @Inject
    @DBConnection
    private Connection connection;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            Connection conn = this.connection;
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            filterChain.doFilter(servletRequest, servletResponse);
            conn.commit();
        } catch (SQLException e) {

            ((HttpServletResponse) servletResponse)
                    .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
