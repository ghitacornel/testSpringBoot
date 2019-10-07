package beans.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher containerPublisher;

    public void publishEvent(String message) {
        System.out.println("Publishing custom event with message : " + message);
        CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
        containerPublisher.publishEvent(customSpringEvent);
    }

}
