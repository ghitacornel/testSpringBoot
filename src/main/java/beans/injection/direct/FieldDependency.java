package beans.injection.direct;

import org.springframework.stereotype.Component;

/**
 * this is a field dependency for another bean. note that there is no need to have it public
 */
@Component
class FieldDependency {

    public String execute() {
        return this + " executed";
    }
}
