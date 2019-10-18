package beans.servlets;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean servletAnnotationExample(CustomServlet customServlet) {
        // mandatory specify the mapping URL
        ServletRegistrationBean bean = new ServletRegistrationBean(customServlet, "/customServletURL");
        bean.setLoadOnStartup(1);
        return bean;
    }

}
