package com.xiao5.mannager.core.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: moc
 * @date: 2018/11/8 4:56 PM
 */
public class RedisHelper {

    public static final Charset utf8 = Charset.forName("utf8");

    /** 设置值 */
    public static void set(RedisTemplate<String, String> redis, final String hkey, final String value) {
        redis.opsForValue().set(hkey, value);
    }

    /** 设置值-并设置过期时间 */
    public static void set(RedisTemplate<String, String> redis, final String hkey, final String value, final Long timeout, final TimeUnit unit) {
        redis.opsForValue().set(hkey, value, timeout, unit);
    }

    /** 获取值 */
    public static String get(RedisTemplate<String, String> redis, final String hmKey){
        return redis.opsForValue().get(hmKey);
    }

    /** 获取值 */
    public static <T> T get(RedisTemplate<String, String> redis, final String hmKey, Class<T> clazz){
        String str = get(redis, hmKey);
        if (!StringHelper.isEmpty(str)) {
            return JsonHelper.jsonToObject(str, clazz);
        }
        return null;
    }

    /** 设置值,如果存在，则忽略并返回false */
    public static boolean setNX(RedisTemplate<String, String> redis, final String hkey, final String value) {
        return redis.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(hkey.getBytes(utf8), value.getBytes(utf8));
            }
        });
    }

    /** 设置值-并设置过期时间,如果存在，则忽略并返回false */
    public static boolean setNX(RedisTemplate<String, String> redis, final String hkey, final String value, final Long timeout) {
        Boolean ret = redis.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(hkey.getBytes(utf8), value.getBytes(utf8));
            }
        });
        return ret && redis.expire(hkey, timeout, TimeUnit.SECONDS);
    }

    /** 设置hash值 */
    public static void hSet(RedisTemplate<String, String> redis, final String hkey, final String key, final String value) {
        redis.opsForHash().put(hkey, key, value);
    }

    /** 设置hash值-并设置过期时间 */
    public static void hSet(RedisTemplate<String, String> redis, final String hkey, final String key, final String value, final Long timeout, final TimeUnit unit) {
        redis.opsForHash().put(hkey, key, value);
        redis.expire(hkey, timeout, unit);
    }

    /** 设置hash值，如果已存在，则忽略并返回false */
    public static boolean sAdd(RedisTemplate<String, String> redis, final String hkey,
                               final String value) {
        Long ret = redis.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.sAdd(hkey.getBytes(utf8),value.getBytes(utf8));
            }
        });
        return ret.intValue() != 0;
    }

    /** 设置hash值-并设置过期时间，如果已存在，则忽略并返回false */
    public static boolean sAdd(RedisTemplate<String, String> redis, final String hkey,
                               final String value, final Long timeout, final TimeUnit unit) {
        Long ret = redis.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.sAdd(hkey.getBytes(utf8), value.getBytes(utf8));
            }
        });
        if(ret.intValue() == 0)
            return false;
        return redis.expire(hkey, timeout, unit);
    }

    /** 删除hash中某个field,不存在该field，则返回false */
    public static boolean sRem(RedisTemplate<String, String> redis, final String hkey, final String value) {
        Long ret = redis.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.sRem(hkey.getBytes(utf8), value.getBytes(utf8));
            }
        });
        return ret.intValue() == 0;
    }

    /**
     * 过期key
     */
    public static boolean expire(RedisTemplate<String, String> redis, final String key, Long timeout) {
        return redis.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 过期key
     */
    public static boolean expire(RedisTemplate<String, String> redis, final String key) {
        return redis.expire(key, 0, TimeUnit.SECONDS);
    }

    /** 验证是否过期 */
    public static boolean isExpired(RedisTemplate<String, String> redis, final String key) {
        return redis.hasKey(key) && redis.getExpire(key) > 0;
    }

    public static boolean deleteSet(RedisTemplate<String, String> redis, final String hkey, final String field) {
        Long ret = redis.opsForSet().remove(hkey, field);
        if(ret > 0)
            return true;
        return false;
    }


    public static void delete(RedisTemplate<String, String> redis, final String hkey, final String field) {
        redis.opsForHash().delete(hkey, field);
    }

    public static void delete(RedisTemplate<String, String> redis, final String hkey) {
        redis.delete(hkey);
    }

    /** 获取hash所有数据 */
    public static Map<Object, Object> hGetAll(RedisTemplate<String, String> redis, final String hkey) {
        return redis.opsForHash().entries(hkey);
    }

    /** 获取hash某个值 */
    public static Object hGet(RedisTemplate<String, String> redis, final String hkey, final String field) {
        return redis.opsForHash().get(hkey, field);
    }

    /** 判断set中是否存在某个值 */
    public static Boolean sIsMember(RedisTemplate<String, String> redis, final String key, final String value) {
        return redis.opsForSet().isMember(key, value);
    }

    /** 判断是否存在某key */
    public static Boolean sIsMember(RedisTemplate<String, String> redis, final String key) {
        return StringHelper.isEmpty(redis.opsForValue().get(key));
    }

    /** 插入到list */
    public static void addToList(RedisTemplate<String, String> redis, final String key, final String value) {
        redis.opsForList().rightPush(key, value);
    }

    /** 查询数据 */
    public static List<String> rangeForList(RedisTemplate<String, String> redis, final String key, final int start, final int end) {
        return redis.opsForList().range(key, start, end);
    }

    /** 删除数据 */
    public static void removeFromList(RedisTemplate<String, String> redis, final String key, final long i, final String value) {
        redis.opsForList().remove(key, i, value);
    }

    /**
     * 向zset添加一个或多个成员，或者更新已存在成员的分数
     * @Description:
     * @Title: zAdd
     * @param @param redis
     * @param @param key
     * @param @param value
     * @param @param score
     * @param @return
     * @return Boolean
     * @throws
     */
    public static Boolean zAdd(RedisTemplate<String, String> redis, final String key, final Object value, final double score) {
        return redis.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                String v;
                if (value instanceof Object) {
                    v= JsonHelper.objectToJson(value);
                }else{
                    v=value.toString();
                }
                return connection.zAdd(key.getBytes(utf8), score, v.getBytes(utf8));
            }
        });
    }

    /**
     * 移除有序集合中给定的字典区间的所有成员
     * @Description:
     * @Title: zRem
     * @param @param redis
     * @param @param key
     * @param @param min
     * @param @param max
     * @param @return
     * @return Long
     * @throws
     */
    public static Long zRemRangeByScore(RedisTemplate<String, String> redis, final String key, final long min, final long max) {
        return redis.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.zRemRange(key.getBytes(utf8), min, max);
            }
        });
    }

    /**
     * 移除zset集合中一个或多个成员
     * @Description:
     * @Title: zRem
     * @param @param redis
     * @param @param key
     * @param @param value
     * @param @return
     * @return Long
     * @throws
     */
    public static Long zRem(RedisTemplate<String, String> redis, final String key, final Object value) {
        return redis.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                String v;
                if (value instanceof Object) {
                    v=JsonHelper.objectToJson(value);
                }else{
                    v=value.toString();
                }
                return connection.zRem(key.getBytes(utf8), v.getBytes(utf8));
            }
        });
    }

    /**
     * 通过索引区间返回zset指定区间内的成员
     * @Description:
     * @Title: zRange
     * @param @param redis
     * @param @param key
     * @param @param begin
     * @param @param end
     * @param @return
     * @return List<String>
     * @throws
     */
    public static List<String> zRange(RedisTemplate<String, String> redis, final String key, final int begin, final int end) {
        return redis.execute(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {

                Set<byte[]> set =  connection.zRange(key.getBytes(utf8), begin, end);
                List<String> list = new ArrayList<String>();
                for (byte[] arr : set) {
                    list.add(new String(arr));
                }
                return list;
            }
        });
    }

    /**
     * 查找zset中指定成员的索引
     * @Description:
     * @Title: zRank
     * @param @param redis
     * @param @param key
     * @param @param value
     * @param @return
     * @return Long
     * @throws
     */
    public static Long zRank(RedisTemplate<String, String> redis, final String key, final Object value) {
        return redis.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                String v;
                if (value instanceof Object) {
                    v=JsonHelper.objectToJson(value);
                }else{
                    v=value.toString();
                }
                Long result =  connection.zRank(key.getBytes(utf8), v.getBytes(utf8));
                return result;
            }
        });
    }

    /**
     * 通过分数获取zset集合指定区间的成员
     * @Description:
     * @Title: zRangeByScore
     * @param @param redis
     * @param @param key
     * @param @param min
     * @param @param max
     * @param @return
     * @return Set<String>
     * @throws
     */
    public static List<String> zRangeByScore(RedisTemplate<String, String> redis, final String key, final long min, final long max) {
        return redis.execute(new RedisCallback<List<String>>() {
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {

                Set<byte[]> set =  connection.zRangeByScore(key.getBytes(utf8), min, max);
                List<String> list = new ArrayList<String>();
                for (byte[] arr : set) {
                    list.add(new String(arr));
                }
                return list;
            }
        });
    }

    /**
     * 获取zset集合的成员数
     * @Description:
     * @Title: zCard
     * @param @param redis
     * @param @param key
     * @param @return
     * @return Long
     * @throws
     */
    public static Long zCard(RedisTemplate<String, String> redis, final String key) {
        return redis.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {

                Long result =  connection.zCard(key.getBytes(utf8));
                return result;
            }
        });
    }

    /**
     * 获取唯一Id
     * @param key
     * @param delta 增加量（不传采用1）
     * @return
     * @throws Exception
     */
    public static String incrementHash(RedisTemplate<String,String> redis, String key, Long delta) {
        try {
            if (null == delta)
                delta=1L;
            return redis.opsForValue().increment(key, delta).toString();
        } catch (Exception e) {
        		//redis宕机时采用uuid的方式生成唯一id
            int first = new Random(10).nextInt(8) + 1;
            int randNo = UUIDHelper.getUUID().hashCode();
            if (randNo < 0)
                randNo = -randNo;
            return first + String.format("%16d", randNo);
        }
    }

    /**
     * 自增值
     * @param redis
     * @param key
     * @param delta 增加量（不传采用1）
     * @return
     */
    public static Long setIncrement(RedisTemplate<String,String> redis, String key, Long delta) {
        try {
            if (null == delta)
                delta=1L;
            return redis.opsForValue().increment(key, delta);
        } catch (Exception e) {
            return null;
        }
    }

}
