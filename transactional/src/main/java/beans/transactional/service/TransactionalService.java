package beans.transactional.service;

import beans.transactional.repository.TransactionalEntityRepository;
import beans.transactional.repository.entity.TransactionalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionalService {

    private final TransactionalEntityRepository repository;
    private final LongRunningService longRunningService;

    @Autowired
    @Lazy// due to injection process
    private TransactionalService SELF;

    public void validate2WritesSeemsOk() {
        repository.save(new TransactionalEntity(1, "data1"));
        repository.save(new TransactionalEntity(2, "data2"));
    }

    public void validate2TransactionsArePresent() {
        repository.save(new TransactionalEntity(1, "data1"));
        if (true) throw new RuntimeException("make sure it fails");
        repository.save(new TransactionalEntity(2, "data2"));
    }

    @Transactional
    public void validate1TransactionIsPresent() {
        repository.save(new TransactionalEntity(1, "data1"));
        if (true) throw new RuntimeException("make sure it fails");
        repository.save(new TransactionalEntity(2, "data2"));
    }

    @Transactional
    public void validateTransactionRequiresNewIsNotHonoredWhenUsingTHIS() {
        repository.save(new TransactionalEntity(1, "data1"));
        methodWithTransactionRequiresNew();
        if (true) throw new RuntimeException("make sure it fails");
    }

    @Transactional
    public void validateTransactionRequiresNewIsHonoredWhenUsingSELF() {
        repository.save(new TransactionalEntity(1, "data1"));
        SELF.methodWithTransactionRequiresNew();
        if (true) throw new RuntimeException("make sure it fails");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void methodWithTransactionRequiresNew() {
        repository.save(new TransactionalEntity(2, "data2"));
    }

    @Transactional
    public void validateModifyWithLock(Integer id) {
        TransactionalEntity entity = repository.getByIdWithLock(id);
        entity.setData(entity.getData() + " " + id);

        // wait in order to simulate a long-running business flow
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException("error waiting ", e);
        }
    }

    @Transactional(timeout = 10)
    public String verifyTimeout(Integer seconds) {
        return longRunningService.longRunningBusiness(seconds);
    }

}
