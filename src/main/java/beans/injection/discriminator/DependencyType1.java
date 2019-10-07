package beans.injection.discriminator;

import org.springframework.stereotype.Component;

@Component
@Type1Qualifier
public class DependencyType1 implements CommonDependencyType {
}
