package beans.rest.retry.thirdparty;

import beans.rest.retry.exceptions.ResourceException;
import org.springframework.stereotype.Service;

/**
 * provides access to an unstable resource
 */
@Service
public class ThirdPartyResource {

    public String stableResource() {
        return "stable resource";
    }

    public String resourceFailBasedOnParameter(boolean parameter) {
        if (parameter) throw new ResourceException("resource unavailable");
        return "resource with temporary issues";
    }

}
