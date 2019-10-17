package beans.servlets;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean servletAnnotationExample() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new CustomServlet(), "/AnnotationExample");
        bean.setLoadOnStartup(1);
        return bean;
    }

}
