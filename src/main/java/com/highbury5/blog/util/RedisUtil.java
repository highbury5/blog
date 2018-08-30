package com.highbury5.blog.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RedisUtil {

    private static JedisPool jedisPool;

    private static final int START = 0;
    private static final int END  = 10;

    /**
     * 初始化redis连接池
     */
    private static void init(){
        jedisPool = new JedisPool("redis://XX");

    }

    /**
     * 向对应的zset中元素score加1,返回最新score
     */
    public static double zincrby(String key ,String value){
        if(jedisPool == null){
            init();
        }
        Jedis jedis = jedisPool.getResource();
        double result = jedis.zincrby(key,1,value);
        jedis.close();
        return result;
    }

    public static Map<String,Double> zrevrange(String key){
         return zrevrange(key,START,END);
    }

    public static Map<String,Double> zrevrange(String key ,int start,int end){
        if(jedisPool == null){
            init();
        }
        Jedis jedis = jedisPool.getResource();
        Set<Tuple> set =  jedis.zrevrangeWithScores(key,start,end);
        Map<String,Double> map = new LinkedHashMap();
        for(Tuple tuple : set){
            map.put(tuple.getElement(),tuple.getScore());
        }
        return map;
    }


}
