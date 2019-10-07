package beans.injection.interfaceimplementation;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope// ensure no instance is available for injection
public class Source implements SourceInterface {
}
