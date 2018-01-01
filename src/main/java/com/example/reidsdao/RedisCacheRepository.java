package com.example.reidsdao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisCacheRepository {

    @Resource(name="redisTemplate")
    private RedisTemplate<String, String> template;

    private ValueOperations<String, String> operations;

    @PostConstruct
    public void init() {
        operations = getTemplate().opsForValue();
    }

    public void set(String key, String value) {
        operations.set(key, value);
    }

    public void set(String key, String value, Long expire) {
        operations.set(key, value, expire, TimeUnit.SECONDS);
    }

    public String get(String key) {
       return operations.get(key);
    }

    public void delete(String key) {
        getTemplate().delete(key);
    }

    public Long incrBy(String key,long delta ) {
       return operations.increment(key,delta);
    }

    public List<Boolean> hasKeys(List<String> keys){
        List<Boolean> keysExist = new ArrayList<Boolean>();
        for(String key : keys){
            Boolean isExist = getTemplate().hasKey(key);
            if(null == isExist){
                isExist = false;
            }
            keysExist.add(isExist);
        }
        return keysExist;
    }

    public RedisTemplate<String, String> getTemplate() {
        return template;
    }

    public void setTemplate(RedisTemplate<String, String> template) {
        this.template = template;
    }
}