package beans.service;

import com.tutorialspoint.helloworld.HelloWorldPortType;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService implements HelloWorldPortType {
    @Override
    public String greetings(String arg0) {
        return "hello" + arg0;
    }
}
