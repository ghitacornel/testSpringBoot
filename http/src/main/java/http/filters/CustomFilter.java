package http.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomFilter extends HttpFilter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request instanceof HttpServletRequest ?
                ((HttpServletRequest) request).getRequestURL().toString() : "N/A";
        System.out.println("from filter, processing url: " + url);
        request.setAttribute("attributeAddedByFilter", "filterAddedValue");
        chain.doFilter(request, response);
    }

}
