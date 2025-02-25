package beans;

import beans.service.RedisService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@Import(TestRedisConfiguration.class)
@SpringBootTest
public class RedisTestEmbedded {

    @Autowired
    RedisService redisService;

    @Test
    public void test() {
        redisService.write("key", "value");
        String value = redisService.read("key");
        Assertions.assertEquals("value", value);
    }

}
