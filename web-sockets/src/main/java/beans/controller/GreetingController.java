package beans.controller;

import beans.message.GreetingMessage;
import beans.message.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingMessage greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return GreetingMessage.builder()
                .content("Hello, " + message.getName() + "!")
                .build();
    }

}