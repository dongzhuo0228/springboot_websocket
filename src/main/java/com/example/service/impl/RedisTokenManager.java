package com.example.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.Constants;
import com.example.domain.TokenModel;
import com.example.reidsdao.RedisCacheRepository;
import com.example.service.TokenManager;

/**
 * 通过Redis存储和验证token的实现类
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class RedisTokenManager implements TokenManager {
    
	@Autowired
    private RedisCacheRepository redis;


    public TokenModel createToken(long userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        token = userId+"_"+token;
        TokenModel model = new TokenModel(userId, token);
        //存储到redis并设置过期时间
        redis.set(String.valueOf(userId),token, 2*60L);
        return model;
    }

    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.get(String.valueOf(model.getUserId()));
        if (token == null || !token.split("_")[1].equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.set(String.valueOf(model.getUserId()),token, 2*60L);
        return true;
    }

    public void deleteToken(long userId) {
        redis.delete(String.valueOf(userId));
    }
}