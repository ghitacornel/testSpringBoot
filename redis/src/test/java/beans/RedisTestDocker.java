package beans;

import beans.service.RedisService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTestDocker {

    @Autowired
    RedisService redisService;

    @Test
    public void test() {
        redisService.write("key", "value");
        String value = redisService.read("key");
        Assertions.assertEquals("value", value);
    }

}
