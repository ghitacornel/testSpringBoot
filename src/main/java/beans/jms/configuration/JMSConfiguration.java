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

    @Bean
    public Queue queue1() {
        return new ActiveMQQueue("simple-jms-queue-1");
    }

    @Bean
    public Queue queue2() {
        return new ActiveMQQueue("simple-jms-queue-2");
    }

    @Bean
    public Queue queueWithTopic() {
        return new ActiveMQQueue("jms-queue-with-topic");
    }

    @Bean
    public JmsListenerContainerFactory<?> queueConnectionFactory(ConnectionFactory connectionFactory,
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
    public JmsTemplate jmsTemplate(ConnectionFactory queueConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(queueConnectionFactory);
        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory topicConnectionFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setPubSubDomain(true);
        return containerFactory;
    }

    @Bean
    public JmsTemplate jmsTemplateTopic(ConnectionFactory topicConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(topicConnectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    @Bean
    Topic topic(JmsTemplate jmsTemplateTopic) throws JMSException {
        return jmsTemplateTopic.getConnectionFactory()
                .createConnection()
                .createSession()
                .createTopic("spring");
    }

}
