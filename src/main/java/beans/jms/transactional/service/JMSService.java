package beans.jms.transactional.service;

import beans.jms.transactional.producer.JMSProducerNonTransactionalQueue;
import beans.jms.transactional.producer.JMSProducerTransactionalQueue;
import beans.jms.transactional.repository.JMSEntityRepository;
import beans.jms.transactional.repository.entity.JMSEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JMSService {

    private final JMSEntityRepository repository;
    private final JMSProducerTransactionalQueue producerTransactional;
    private final JMSProducerNonTransactionalQueue producerNonTransactional;

    @Transactional
    public void triggerTransaction(boolean ok) {
        JMSEntity jmsEntity = new JMSEntity();
        jmsEntity.setId(1);
        jmsEntity.setMessage("message");
        repository.save(jmsEntity);
        producerTransactional.createMessageAndSendItToTheTransactionalQueue();
        if (!ok) throw new RuntimeException("controlled failure");
    }

    @Transactional
    public void triggerTransactionNonTransactional(boolean ok) {
        JMSEntity jmsEntity = new JMSEntity();
        jmsEntity.setId(1);
        jmsEntity.setMessage("message");
        repository.save(jmsEntity);
        producerNonTransactional.createMessageAndSendItToTheTransactionalQueue();
        if (!ok) throw new RuntimeException("controlled failure");
    }

}
