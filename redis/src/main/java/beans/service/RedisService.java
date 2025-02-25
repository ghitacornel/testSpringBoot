package beans.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void write(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String read(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
