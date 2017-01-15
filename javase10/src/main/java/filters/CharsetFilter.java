package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(value = "/*")
public class CharsetFilter implements   Filter {

    private String baseEncoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        baseEncoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(baseEncoding);
        response.setCharacterEncoding(baseEncoding);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
