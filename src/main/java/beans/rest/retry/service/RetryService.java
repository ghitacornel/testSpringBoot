package beans.rest.retry.service;

import beans.rest.retry.exceptions.RecoverableResourceException;
import beans.rest.retry.thirdparty.ThirdPartyResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetryService {

    private final ThirdPartyResource thirdPartyResource;

    public String stableResource() {
        return thirdPartyResource.stableResource();
    }

    @Retryable(value = RecoverableResourceException.class, backoff = @Backoff(delay = 100))
    public String resourceFailBasedOnParameter(boolean parameter) {
        log.info("retrying ...");
        return thirdPartyResource.resourceFailBasedOnParameterWithRecoverableError(parameter);
    }

    @Recover
    public String recover(RecoverableResourceException e, boolean parameter) {
        return e.getMessage() + " for now, returning default for parameter=" + parameter;
    }

    @Retryable
    public String resourceFailWithNoBackup() {
        log.info("retrying ...");
        return thirdPartyResource.resourceFailBasedOnParameterWithUnrecoverableError(true);
    }
}
