package beans.servlets.configuration;

import beans.servlets.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * All servlets and filters configuration are defined here
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

    @Bean
    FilterRegistrationBean customFilter() {
        FilterRegistrationBean<CustomFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CustomFilter());
        bean.setUrlPatterns(Collections.singletonList("/customServletURL/*"));
        return bean;
    }

    @Bean
    FilterRegistrationBean customFilterPOST() {
        FilterRegistrationBean<CustomFilterPOST> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CustomFilterPOST());
        bean.setUrlPatterns(Collections.singletonList("/customServletURLPOST/*"));
        return bean;
    }

}
