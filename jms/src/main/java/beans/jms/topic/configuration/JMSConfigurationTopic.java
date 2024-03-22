package beans.jms.topic.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Topic;

@Configuration
public class JMSConfigurationTopic {

    public static final String TOPIC_NAME = "topicName";

    @Bean
    Topic topic(@Qualifier("jmsTemplateTopic") JmsTemplate jmsTemplateTopic) throws JMSException {
        return jmsTemplateTopic.getConnectionFactory()
                .createConnection()
                .createSession()
                .createTopic(TOPIC_NAME);
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
        return new JmsTemplate(topicConnectionFactory);
    }

}
