package com.barry.cloud.platform.redis.service;

import com.barry.cloud.platform.common.id.IdWorker;
import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.redis.base.BaseTest;
import com.barry.cloud.platform.redis.entity.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/10 15:01
 */
@Slf4j
public class RedisServiceTest extends BaseTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testIdWork(){
        IdWorker idWorker = new IdWorker(1L);
        for (int i=0;i<5;i++){
            log.info("===============>id={}"+idWorker.nextId().toString());
        }
    }

    /**
     * 写入集合
     * 获取结合
     * */
    @Test
    public void testPutGetArray(){
        List<TestEntity> list = new ArrayList<>();
        IdWorker idWorker = new IdWorker(1L);
        TestEntity entity = new TestEntity();
        entity.setId(idWorker.nextId().toString());
        entity.setName("张三");
        entity.setEmail("zhangsan@163.com");
        entity.setMobile("15985374527");
        entity.setAddress("上海市闵行区古美路");

        TestEntity entity2 = new TestEntity();
        entity2.setId(idWorker.nextId().toString());
        entity2.setName("李四");
        entity2.setEmail("lisi@163.com");
        entity2.setMobile("13585428535");
        entity2.setAddress("上海市徐汇区宜山路");

        TestEntity entity3 = new TestEntity();
        entity3.setId(idWorker.nextId().toString());
        entity3.setName("王麻子");
        entity3.setEmail("wangmazi@163.com");
        entity3.setMobile("15811112222");
        entity3.setAddress("上海市黄浦区龙华中路");

        list.add(entity);
        list.add(entity2);
        list.add(entity3);

        String key = new IdWorker(2L).nextId().toString();
        log.info("=================>key={}", key);
        boolean status = redisService.set(key, list);
        log.info("=================>status={}", status);
        List<TestEntity> cacheList = (List<TestEntity>) redisService.get(key);
        log.info("=================>list={}", JSONMapper.writeObjectAsString(cacheList));
    }

    @Test
    public void testStringPutGet(){
        boolean status = redisService.set("好人一生平安", "Hello,中国人民解放军!");
        log.info("=================>status={}", status);
        String value = (String) redisService.get("好人一生平安");
        log.info("=================>value={}", value);
    }






}
