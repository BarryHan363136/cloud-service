package com.barry.cloud.platform.redis.service;

import com.barry.cloud.platform.redis.utils.RedisLockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private JedisPool jedisPool;

    private static final String LOCK_NAME = "LOCK_ID";
    private static final String PATH = "TEST_UUID/TEST";

    @Test
    public void testLock01() {
        Jedis jedis = jedisPool.getResource();
        boolean lockStatus = RedisLockUtils.tryLock(jedis, LOCK_NAME, PATH, 100000);
        if (lockStatus) {
            System.out.println("拿到锁了");
            // debugger
            RedisLockUtils.release(jedis, LOCK_NAME, PATH);
        } else {
            System.out.println("没有拿到锁");
        }
    }

    @Test
    public void testLock02() {
        Jedis jedis = jedisPool.getResource();
        boolean lockStatus = RedisLockUtils.tryLock(jedis, LOCK_NAME, PATH, 5000);
        if (lockStatus) {
            System.out.println("拿到锁了");
            RedisLockUtils.release(jedis, LOCK_NAME, PATH);
        } else {
            System.out.println("没有拿到锁");
        }
    }
}
