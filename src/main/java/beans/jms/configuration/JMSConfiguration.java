package beans.jms.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;

@EnableJms
@Configuration
public class JMSConfiguration {

    public static final String TOPIC_NAME = "spring";

    @Bean
    Queue queue1() {
        return new ActiveMQQueue("simple-jms-queue-1");
    }

    @Bean
    Queue queue2() {
        return new ActiveMQQueue("simple-jms-queue-2");
    }

    @Bean
    Topic topic(JmsTemplate jmsTemplateTopic) throws JMSException {
        return jmsTemplateTopic.getConnectionFactory()
                .createConnection()
                .createSession()
                .createTopic(TOPIC_NAME);
    }

    @Bean
    JmsListenerContainerFactory<?> queueConnectionFactory(ConnectionFactory connectionFactory,
                                                          DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        factory.setPubSubDomain(false);
        factory.setSubscriptionDurable(false);
        return factory;
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory queueConnectionFactory) {
        return new JmsTemplate(queueConnectionFactory);
    }

    @Bean
    DefaultJmsListenerContainerFactory topicConnectionFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setPubSubDomain(true);
        return containerFactory;
    }

    @Bean
    JmsTemplate jmsTemplateTopic(ConnectionFactory topicConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(topicConnectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

}
