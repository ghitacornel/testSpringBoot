package beans.http.servlets.configuration;

import beans.http.servlets.CustomServlet;
import beans.http.servlets.CustomServletGET;
import beans.http.servlets.CustomServletPOST;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * All servlets configuration are defined here
 */
@Configuration
public class ServletsConfiguration {

    @Bean
    ServletRegistrationBean<CustomServlet> customServlet() {
        // mandatory specify the mapping URL
        ServletRegistrationBean<CustomServlet> bean = new ServletRegistrationBean<>(new CustomServlet(), "/customServletURL");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    ServletRegistrationBean<CustomServletGET> customServletGET() {
        // mandatory specify the mapping URL
        ServletRegistrationBean<CustomServletGET> bean = new ServletRegistrationBean<>(new CustomServletGET(), "/customServletURLGET");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    ServletRegistrationBean<CustomServletPOST> customServletPOST() {
        // mandatory specify the mapping URL
        ServletRegistrationBean<CustomServletPOST> bean = new ServletRegistrationBean<>(new CustomServletPOST(), "/customServletURLPOST");
        bean.setLoadOnStartup(1);
        return bean;
    }

}
