package beans.jms.transactional;

import beans.jms.transactional.consumer.JMSConsumerTransactionalQueue;
import beans.jms.transactional.model.JMSMessageTransactionalQueue;
import beans.jms.transactional.repository.JMSEntityRepository;
import beans.jms.transactional.repository.entity.JMSEntity;
import beans.jms.transactional.service.JMSService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;

import java.util.List;

public class JMSServiceTest extends AbstractTestSpringBootContext {

    @Autowired
    JMSService jmsService;

    @Autowired
    JMSEntityRepository repository;

    @Autowired
    JMSConsumerTransactionalQueue consumer;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        consumer.message = null;
    }

    @Test
    public void testTransactionOK() {

        jmsService.triggerTransaction(true);

        List<JMSEntity> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);
        Assertions.assertThat(all.get(0)).isEqualTo(new JMSEntity(1, "message"));

        Assertions.assertThat(consumer.message).isEqualTo(new JMSMessageTransactionalQueue(1, "payload for transactional queue"));
    }

    @Test
    public void testTransactionFail() {

        boolean exceptionRaised = false;
        try {
            jmsService.triggerTransaction(false);
        } catch (Exception e) {
            exceptionRaised = true;
            Assertions.assertThat(e.getMessage()).isEqualTo("controlled failure");
        }
        Assertions.assertThat(exceptionRaised).isTrue();

        List<JMSEntity> all = repository.findAll();
        Assertions.assertThat(all.isEmpty()).isTrue();

        Assertions.assertThat(consumer.message).isNull();
    }

    @Test
    public void testTransactionNotRolledBackAndMessageSent() {

        jmsService.triggerTransactionNonTransactional(true);

        List<JMSEntity> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);
        Assertions.assertThat(all.get(0)).isEqualTo(new JMSEntity(1, "message"));

        Assertions.assertThat(consumer.message).isEqualTo(new JMSMessageTransactionalQueue(1, "payload for transactional queue non transactional"));
    }

    @Test
    public void testTransactionRolledBackButMessageStillSent() {

        boolean exceptionRaised = false;
        try {
            jmsService.triggerTransactionNonTransactional(false);
        } catch (Exception e) {
            exceptionRaised = true;
            Assertions.assertThat(e.getMessage()).isEqualTo("controlled failure");
        }
        Assertions.assertThat(exceptionRaised).isTrue();

        List<JMSEntity> all = repository.findAll();
        Assertions.assertThat(all.isEmpty()).isTrue();

        Assertions.assertThat(consumer.message).isEqualTo(new JMSMessageTransactionalQueue(1, "payload for transactional queue non transactional"));
    }
}
