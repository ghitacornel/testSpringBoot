package beans.rest.interceptors;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpInterceptor implements HandlerInterceptor {

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // add custom logic here
        // see other methods
        if (request.getMethod().equals(HttpMethod.DELETE.name())) {
            if (response.getStatus() == HttpStatus.OK.value()) {
//                response.setStatus(204);
            }
        }
    }

}
