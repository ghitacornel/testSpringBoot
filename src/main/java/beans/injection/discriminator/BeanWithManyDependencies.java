package beans.injection.discriminator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A bean with many dependencies<br>
 * Note that private dependencies are resolved even without provided setters<br>
 * Note that some dependencies cannot be resolved without a discriminator
 */
@Component
public class BeanWithManyDependencies {

    @Type1Qualifier// specific discriminator is used for this dependency
    @Autowired
    private CommonDependencyType dependency1;

    @Type2Qualifier// specific discriminator is used for this dependency
    @Autowired
    private CommonDependencyType dependency2;

    public boolean areDependenciesResolved() {

        if (dependency1 == null) return false;
        if (!dependency1.execute().equals(dependency1 + " executed")) {
            throw new RuntimeException("dependency1 not resolved as expected");
        }

        if (dependency2 == null) return false;
        if (!dependency2.execute().equals(dependency2 + " executed")) {
            throw new RuntimeException("dependency2 not resolved as expected");
        }

        return true;
    }


}
