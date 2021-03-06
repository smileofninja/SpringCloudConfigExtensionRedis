package com.github.springccredis.spring.cloud.config.redis;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

/**
 * @author kumar ( )
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseRedisEnvironmentRepositoryTest {

    @LocalServerPort
    int port;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Before
    public void cleanUpRedis() {
        Set<String> keys = stringRedisTemplate.keys("*");
        stringRedisTemplate.delete(keys);
    }

    protected void injectPropertiesIntoRedis(Map<String, String> properties) {
        stringRedisTemplate.opsForValue().multiSet(properties);
    }
}
