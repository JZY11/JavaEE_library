package demo.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/19.
 * JavaEE_library.
 */
@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }

    public void destroy() {

    }
}
