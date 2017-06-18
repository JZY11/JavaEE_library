package demo.servlet;

import demo.util.Db;
import demo.util.Error;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/16.
 * JavaEE_library.
 */
@WebServlet(urlPatterns = "/user")
public class UserAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("register".equals(action)) {
            try {
                register(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        if ("login".equals(action)) {
            try {
                login(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        if ("logout".equals(action)) {
            logout(req,resp);
            return;
        }
        Error.showError(req,resp);
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");

        Connection connection = Db.getconnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM db_javaee_library.user WHERE username = ?";

        try {

            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                Error.showError(req, resp);
                return;
            }

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                req.setAttribute("message", "用户名已存在");
                return;
            }

            sql = "INSERT INTO db_javaee_library.user(username,password)VALUE(?,?) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

            resp.sendRedirect("default.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, connection);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");

        Connection connection = Db.getconnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM db_javaee_library.user WHERE username = ? AND password = ?";

        try {

            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                Error.showError(req, resp);
                return;
            }

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                req.getSession().setAttribute("username", username);
                req.getSession().setAttribute("role", role);

                if ("用户".equals(role)) {
                    int userId = resultSet.getInt("id");
                    req.getSession().setAttribute("userId", userId);
                    resp.sendRedirect("index.jsp");
                    return;
                }

                if ("管理员".equals(role)) {
                    resp.sendRedirect("book?action=queryAll");
                    return;
                }
                Error.showError(req, resp);
            } else {
                req.setAttribute("message", "用户名或密码错误");
                req.getRequestDispatcher("default.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, connection);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
            req.getSession().invalidate();
            resp.sendRedirect("default.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
