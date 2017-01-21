package filters;


import com.google.inject.Singleton;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Singleton
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest;
        if (servletRequest instanceof HttpServletRequest)
            httpRequest = (HttpServletRequest) servletRequest;
        else
            throw new ServletException("servletRequest is not instance of the HttpServletRequest");

        HttpSession session = httpRequest.getSession();

        String sessionLocale = (String) session.getAttribute("locale");
        String locale = (String) httpRequest.getParameter("locale");

        if (locale != null || sessionLocale == null) {
            if (locale == null) {
                String acceptLocales = httpRequest.getHeader("Accept-Language");
                if (acceptLocales != null)
                    locale = acceptLocales.substring(0, acceptLocales.indexOf("-"));
                else
                    locale = "en";
            }
            session.setAttribute("locale", locale);
        }


        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
