package beans.rest.retry.service;

import beans.rest.retry.exceptions.ResourceException;
import beans.rest.retry.thirdparty.ThirdPartyResource;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetryService {

    private final ThirdPartyResource thirdPartyResource;

    public String stableResource() {
        return thirdPartyResource.stableResource();
    }

    @Retryable(value = ResourceException.class)
    public String resourceFailBasedOnParameter(boolean parameter) {
        return thirdPartyResource.resourceFailBasedOnParameter(parameter);
    }

    @Recover
    public String recover(ResourceException e, boolean parameter) {
        return e.getMessage() + " for now, returning default for parameter=" + parameter;
    }
}
