package beans.rest.swagger.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * documentation generated at : http://localhost:8081/v3/api-docs/
 * yaml documentation available at : http://localhost:8081/v3/api-docs.yaml
 * Swagger available at : http://localhost:8081/swagger-ui/index.html
 * Custom url : springdoc.api-docs.path=/api-docs
 * Disabling the /v3/api-docs endpoint : springdoc.api-docs.enabled=false
 * Disabling the swagger-ui : springdoc.swagger-ui.enabled=false
 * Packages to include : springdoc.packagesToScan=com.package1, com.package2
 * Paths to include : springdoc.pathsToMatch=/v1, /api/balance/**
 */
@OpenAPIDefinition(info = @Info(title = "Custom REST Documentation title", description = "All REST API custom description", version = "1"))
@Configuration
public class SwaggerConfiguration {
}
