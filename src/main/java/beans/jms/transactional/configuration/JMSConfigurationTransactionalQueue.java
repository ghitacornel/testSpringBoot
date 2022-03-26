package beans.jms.transactional.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@EnableJms
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
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    JmsTemplate jmsTemplateTransactionalQueue(ConnectionFactory transactionalQueueConnectionFactory) {
        return new JmsTemplate(transactionalQueueConnectionFactory);
    }

}
