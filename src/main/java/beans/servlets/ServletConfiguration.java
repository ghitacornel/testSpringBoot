package beans.servlets;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean<CustomServlet> servletAnnotationExample(CustomServlet customServlet) {
        // mandatory specify the mapping URL
        ServletRegistrationBean<CustomServlet> bean = new ServletRegistrationBean<>(customServlet, "/customServletURL");
        bean.setLoadOnStartup(1);
        return bean;
    }

}
