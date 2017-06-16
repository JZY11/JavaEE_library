package demo.util;

import com.mysql.jdbc.Driver;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/16.
 * JavaEE_library.
 */
public class Db {
    private static final String URL = "jdbc:mysql///?user=root&password=system"
    public static Connection getconnection(){
        try {
            new Driver();
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement,Connection connection){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
