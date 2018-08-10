package com.barry.cloud.platform.redis.service;

import java.util.List;
import java.util.Set;

public interface RedisService {

    public boolean set(String key, Object value);

    public boolean set(String key, Object value, Long expireTime);

    public void remove(String... keys);

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(String pattern);

    /**
     * 删除对应的value
     * @param key
     */
    public void remove(String key);
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(String key);

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(String key);
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value);

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey);

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k, Object v);

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1);

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void setArray(String key, Object value);

    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> getArray(String key);

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure);

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1);

}
