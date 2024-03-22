package http.interceptors;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HttpInterceptor implements HandlerInterceptor {

    // intercept all
    // see other methods available for overriding
    // seems impossible to alter the content of response
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        // add custom logic here
        // see other methods
        System.err.println(request.getMethod());
        System.err.println("custom business");
        System.err.println(response.getStatus());
    }

}
