package demo.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/16.
 * JavaEE_library.
 */
public class Error {
    public static void showError(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException{
        req.setAttribute("message", "Error...");
        req.getRequestDispatcher("default.jsp");
    }
}
