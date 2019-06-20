package com.barry.cloud.platform.jediscluster.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Tongshan.Han
 * @time: 2019/6/19 14:54
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisClusterTest {

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisSetData(){
        redisTemplate.opsForValue().set("testKey", "中国人民解放军2");
    }

    @Test
    public void testRedisStorage(){
        Map map = new HashMap();
        map.put("name", "张三");
        map.put("age", 10);
        redisTemplate.opsForHash().putAll("person", map);
        log.info("===111=========>"+redisTemplate.opsForHash().values("person"));
        log.info("===222=========>"+redisTemplate.opsForHash().entries("person"));
        log.info("===333=========>"+redisTemplate.opsForHash().get("person", "name"));
    }


}
