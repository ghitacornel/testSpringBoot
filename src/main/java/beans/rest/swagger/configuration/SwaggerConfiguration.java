package beans.rest.swagger.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Custom REST Documentation title", description = "All REST API custom description", version = "1"))
@Configuration
public class SwaggerConfiguration {
}
