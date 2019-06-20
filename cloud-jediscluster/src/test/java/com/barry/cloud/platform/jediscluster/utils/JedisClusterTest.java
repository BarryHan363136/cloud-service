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
        Long h = redisTemplate.opsForSet().add("testKey", "中国人民解放军");
        log.info("=======================>"+h);
    }

    @Test
    public void testRedisStorage(){
        List list = (List) redisTemplate.opsForHash().get("seckill", 1);
        log.info("=======================>"+JSON.toJSONString(list));
    }


}
