package beans.retry.controller;

import beans.retry.repository.entity.RetryEntity;
import beans.retry.service.RetryService;
import beans.retry.service.RetryServiceTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("retry")
@RequiredArgsConstructor
public class RetryController {

    private final RetryService retryService;
    private final RetryServiceTransactional retryServiceTransactional;

    @GetMapping("stableResource")
    public String stableResource() {
        return retryService.stableResource();
    }

    @GetMapping("resourceFailBasedOnParameter/{parameter}")
    public String resourceFailBasedOnParameter(@PathVariable(name = "parameter") Boolean parameter) {
        return retryService.resourceFailBasedOnParameter(parameter);
    }

    @GetMapping("resourceFailWithNoBackup")
    public String resourceFailWithNoBackup() {
        return retryService.resourceFailWithNoBackup();
    }

    @GetMapping("findAll")
    public List<RetryEntity> findAll() {
        return retryServiceTransactional.findAll();
    }

    @GetMapping("deleteAll")
    public void deleteAll() {
        retryServiceTransactional.deleteAll();
    }

    @GetMapping("testTransactionIsNotRolledBackWhenDefaultExists")
    public void testTransactionIsNotRolledBackWhenDefaultExists() {
        retryServiceTransactional.testTransactionIsNotRolledBackWhenDefaultExists();
    }
}
