package beans.thirdparty;

import beans.retry.exceptions.RecoverableResourceException;
import beans.retry.exceptions.UnrecoverableResourceException;
import org.springframework.stereotype.Service;

/**
 * provides access to an unstable resource
 */
@Service
public class ThirdPartyResource {

    public String stableResource() {
        return "stable resource";
    }

    public String resourceFailBasedOnParameterWithRecoverableError(boolean parameter) {
        if (parameter) throw new RecoverableResourceException("resource unavailable");
        return "resource with temporary issues";
    }

    public String resourceFailBasedOnParameterWithUnrecoverableError(boolean parameter) {
        if (parameter) throw new UnrecoverableResourceException("resource unavailable and unrecoverable");
        return "resource with temporary issues";
    }

}
