package beans.thirdparty.service;

import beans.thirdparty.exceptions.RecoverableResourceException;
import beans.thirdparty.exceptions.UnrecoverableResourceException;
import org.springframework.stereotype.Service;

/**
 * provides access to an unstable resource
 */
@Service
public class ThirdPartyResourceService {

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
