package beans.injection.direct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * a bean with dependencies<br>
 * providing dependencies setters is optional.
 * provided dependencies setters will be used though for injection.
 * providing dependencies getters is forbidden and dangerous, because the injected resources are to be managed solely by
 * the container and not further exposed out of the means provided by the container.
 */
@Component
public class BeanWithDifferentTypesOfDependencies {

    // no getters or setters are provided or used
    @Autowired
    private FieldDependency fieldDependency;

    // only a setters is provided and used
    private SetterDependency setterDependency;

    // only a constructor parameter is provided and used
    // no getters or setters are provided or used
    final private ConstructorDependency constructorDependency;

    // used only for template purpose
    private boolean setterUsedInInjection = false;
    private boolean constructorUsedInInjection;

    @Autowired
    public BeanWithDifferentTypesOfDependencies(ConstructorDependency constructorDependency) {
        this.constructorDependency = constructorDependency;
        constructorUsedInInjection = true;
    }

    public boolean areDependenciesResolved() {

        if (fieldDependency == null) return false;
        if (!fieldDependency.execute().equals(fieldDependency + " executed")) {
            throw new RuntimeException("fieldDependency not resolved as expected");
        }

        if (setterDependency == null) return false;
        if (!setterUsedInInjection) {
            throw new RuntimeException("setterDependency didn't use the setter");
        }
        if (!setterDependency.execute().equals(setterDependency + " executed")) {
            throw new RuntimeException("setterDependency not resolved as expected");
        }

        if (constructorDependency == null) return false;
        if (!constructorUsedInInjection) {
            throw new RuntimeException("constructorDependency didn't use the constructor");
        }
        if (!constructorDependency.execute().equals(constructorDependency + " executed")) {
            throw new RuntimeException("constructorDependency not resolved as expected");
        }

        return true;
    }

    @Autowired
    public void setSetterDependency(SetterDependency setterDependency) {
        this.setterDependency = setterDependency;
        setterUsedInInjection = true;
    }
}
