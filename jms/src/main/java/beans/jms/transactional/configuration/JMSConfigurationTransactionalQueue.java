package beans.jms.transactional.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;

@Configuration
public class JMSConfigurationTransactionalQueue {

    public static final String TRANSACTIONAL_QUEUE = "transactionalQueue";

    @Bean
    Queue transactionalQueue() {
        return new ActiveMQQueue(TRANSACTIONAL_QUEUE);
    }

    @Bean
    JmsListenerContainerFactory<?> transactionalQueueConnectionFactory(ConnectionFactory connectionFactory,
                                                                       DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // can use and inject a custom org.springframework.transaction.PlatformTransactionManager
        // factory.setTransactionManager(platformTransactionManager);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    JmsTemplate jmsTemplateTransactional(ConnectionFactory transactionalQueueConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(transactionalQueueConnectionFactory);
        // make it "transactional"
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    @Bean
    JmsTemplate jmsTemplateNonTransactional(ConnectionFactory transactionalQueueConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(transactionalQueueConnectionFactory);
        // make it "non-transactional", which is by default
        jmsTemplate.setSessionTransacted(false);
        return jmsTemplate;
    }

}
