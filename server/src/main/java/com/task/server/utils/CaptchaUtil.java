package com.task.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
public class CaptchaUtil {

    @Autowired
    private static RedisTemplate redisTemplate;

	private static final String prefix = "CAPTCHA_";
	
	public static boolean validate(String sessionId, String value){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String key = prefix + sessionId;
		String captcha = (String) valueOperations.get(key);
		return captcha != null && captcha.equalsIgnoreCase(value);
	}
}