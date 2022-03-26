package beans.jms.topic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Topic;

@EnableJms
@Configuration
public class JMSConfigurationTopic {

    public static final String TOPIC_NAME = "topicName";

    @Bean
    Topic topic(JmsTemplate jmsTemplateTopic) throws JMSException {
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
