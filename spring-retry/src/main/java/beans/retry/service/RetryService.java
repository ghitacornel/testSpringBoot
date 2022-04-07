package beans.retry.service;

import beans.thirdparty.exceptions.RecoverableResourceException;
import beans.thirdparty.service.ThirdPartyResourceService;
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

    private final ThirdPartyResourceService thirdPartyResourceService;

    public String stableResource() {
        return thirdPartyResourceService.stableResource();
    }

    @Retryable(value = RecoverableResourceException.class, backoff = @Backoff(delay = 100), maxAttempts = 10)
    public String resourceFailBasedOnParameter(boolean parameter) {
        log.info("retrying ...");
        return thirdPartyResourceService.resourceFailBasedOnParameterWithRecoverableError(parameter);
    }

    @Recover
    public String recover(RecoverableResourceException e, boolean parameter) {
        return e.getMessage() + " for now, returning default for parameter=" + parameter;
    }

    @Retryable(backoff = @Backoff(delay = 100), maxAttempts = 10)
    public String resourceFailWithNoBackup() {
        log.info("retrying ...");
        return thirdPartyResourceService.resourceFailBasedOnParameterWithUnrecoverableError(true);
    }
}
