package beans.servlets;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class ServletsConfiguration {

    @Bean
    public ServletRegistrationBean<CustomServlet> myServletRegistration() {
        // mandatory specify the mapping URL
        ServletRegistrationBean<CustomServlet> bean = new ServletRegistrationBean<>(new CustomServlet(), "/customServletURL");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean<CustomFilter> frb = new FilterRegistrationBean<>();
        frb.setFilter(new CustomFilter());
        frb.setUrlPatterns(Collections.singletonList("/customServletURL/*"));
        return frb;
    }

}
