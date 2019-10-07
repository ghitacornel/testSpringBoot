package beans.scopes.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * all Spring beans are singletons by default<br>
 * In order to mark them as prototype the extra @Scope marker is used
 */
@Component
@Scope("prototype")
public class PrototypeBean {
}
