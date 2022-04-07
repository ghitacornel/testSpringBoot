package http.filters.configuration;

import http.filters.CustomFilter;
import http.filters.CustomFilterPOST;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * All filters configuration are defined here
 */
@Configuration
public class FiltersConfiguration {

    @Bean
    FilterRegistrationBean<CustomFilter> customFilter() {
        FilterRegistrationBean<CustomFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CustomFilter());
        // filters are wrapping something
        bean.setUrlPatterns(Collections.singletonList("/customServletURL/*"));
        return bean;
    }

    @Bean
    FilterRegistrationBean<CustomFilterPOST> customFilterPOST() {
        FilterRegistrationBean<CustomFilterPOST> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CustomFilterPOST());
        // filters are wrapping something
        bean.setUrlPatterns(Collections.singletonList("/customServletURLPOST/*"));
        return bean;
    }

}
