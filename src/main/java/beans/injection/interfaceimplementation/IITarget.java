package beans.injection.interfaceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// the target must be a singleton eagerly initialized with no available for injection dependencies
public class IITarget {

    @Autowired
    DirectSource directSource;

    @Autowired
    SourceInterface sourceInterface;

}
