package beans.injection.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Some containers allow a managed bean to obtain a reference to the managing container
 */
@Component
public class BeanWithBeanContainerReferenceInjected {

    @Autowired
    ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

}
