package beans.rest.retry.service;

import beans.rest.retry.entity.RetryEntity;
import beans.rest.retry.repository.RetryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RetryServiceTransactional {

    private final RetryService retryService;
    private final RetryEntityRepository retryEntityRepository;

    public void testTransactionIsNotRolledBackWhenDefaultExists() {
        retryService.resourceFailBasedOnParameter(true);
        retryEntityRepository.save(new RetryEntity(1, "dummy"));
    }

    public List<RetryEntity> findAll() {
        return retryEntityRepository.findAll();
    }

    public void deleteAll() {
        retryEntityRepository.deleteAll();
    }
}
