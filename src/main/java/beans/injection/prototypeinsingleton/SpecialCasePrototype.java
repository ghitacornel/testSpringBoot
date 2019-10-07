package beans.injection.prototypeinsingleton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SpecialCasePrototype {
}
