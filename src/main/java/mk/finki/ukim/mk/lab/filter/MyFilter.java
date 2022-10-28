package mk.finki.ukim.mk.lab.filter;

import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;


@Component
@Order(1)
public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
        String color=httpServletRequest.getParameter("color");
        if (color==null){
            color= (String) httpServletRequest.getSession().getAttribute("color");
        }
        String path=httpServletRequest.getServletPath();
        //System.out.println("Filter");
        if ( !"/".equals(path) && color==null){
            System.out.println(path+" "+color);
            httpServletResponse.sendRedirect("/");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
