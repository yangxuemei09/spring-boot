package com.tangdi.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tangdi.common.common.RedisKey;
import com.tangdi.common.utils.DeflateUtils;
import com.tangdi.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ron
 * @date 2018/12/29 17:34
 */
@Service
public class RedisServiceImpl implements RedisService {
    private static final String CACHE_PREFIX = RedisKey.CACHE_PREFIX;

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;


    @Override
    public boolean setByCompress(final String key, final long seconds, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.setEx(serializer.serialize(CACHE_PREFIX + key), seconds,  DeflateUtils.compress(value.getBytes()));
                return true;
            }
        });
        return result;
    }

    @Override
    public String getByDecompress(final String key){
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(CACHE_PREFIX + key));
                if(value!=null){
                    return new String(DeflateUtils.decompress(value));
                } else {
                    return null;
                }
            }
        });
        return result;
    }


    @Override
    public boolean set(final String key, final long seconds, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.setEx(serializer.serialize(CACHE_PREFIX + key), seconds, serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    @Override
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(CACHE_PREFIX + key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    @Override
    public long incr(final String key) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long result = connection.incr(serializer.serialize(CACHE_PREFIX + key));
                return result;
            }
        });
        return result;
    }

    @Override
    public long incrBy(final String key, final long step) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long result = connection.incrBy(serializer.serialize(CACHE_PREFIX + key), step);
                return result;
            }
        });
        return result;
    }

    @Override
    public long decr(final String key) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long result = connection.decr(serializer.serialize(CACHE_PREFIX + key));
                return result;
            }
        });
        return result;
    }

    @Override
    public long decrBy(final String key, final long step) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long result = connection.decrBy(serializer.serialize(CACHE_PREFIX + key), step);
                return result;
            }
        });
        return result;
    }

    @Override
    public String get(final String key){
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(CACHE_PREFIX + key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public long del(final String key){
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long result = connection.del(serializer.serialize(CACHE_PREFIX + key));
                return result;
            }
        });
        return result;
    }

    @Override
    public boolean hSet(final String key, final String field, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.hSet(serializer.serialize(CACHE_PREFIX + key),
                        serializer.serialize(field),
                        serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    @Override
    public boolean hMSet(final String key, final Map<String, String> map) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                Map<byte[], byte[]> hashes = new HashMap<>();
                for (Map.Entry<String, String> entry: map.entrySet()) {
                    hashes.put(serializer.serialize(entry.getKey()), serializer.serialize(entry.getValue()));
                }
                connection.hMSet(serializer.serialize(CACHE_PREFIX + key),hashes);
                return true;
            }
        });
        return result;
    }

    @Override
    public String hGet(final String key, final String field) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.hGet(serializer.serialize(CACHE_PREFIX + key),
                        serializer.serialize(field));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public long hDel(final String key, final String field) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long result = connection.hDel(serializer.serialize(CACHE_PREFIX + key),
                        serializer.serialize(field));
                return result;
            }
        });
        return result;
    }

    @Override
    public long hLen(final String key) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long result = connection.hLen(serializer.serialize(CACHE_PREFIX + key));
                return result;
            }
        });
        return result;
    }

    @Override
    public Map<String, String> hGetAll(final String key) {
        Map<String, String> result = redisTemplate.execute(new RedisCallback<Map<String, String>>() {
            @Override
            public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                Map<byte[], byte[]> maps = connection.hGetAll(serializer.serialize(CACHE_PREFIX + key));
                Map<String, String> result = new HashMap<>();
                if (maps != null) {
                    for (Map.Entry<byte[], byte[]> entry: maps.entrySet()) {
                        result.put(serializer.deserialize(entry.getKey()), serializer.deserialize(entry.getValue()));
                    }
                }
                return result;
            }
        });
        return result;
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(CACHE_PREFIX + key, expire, TimeUnit.SECONDS);
    }

    @Override
    public <T> boolean setList(String key, List<T> list) {
        String value = JSON.toJSONString(list);
        return set(CACHE_PREFIX + key,value);
    }

    @Override
    public <T> boolean setList(String key, long seconds, List<T> list) {
        String value = JSON.toJSONString(list);
        return set(CACHE_PREFIX + key, seconds, value);
    }

    @Override
    public <T> List<T> getList(final String key, Class<T> clz) {
        String json = get(CACHE_PREFIX + key);
        if(json!=null){
            List<T> list = JSONArray.parseArray(json, clz);
            return list;
        }
        return null;
    }

    @Override
    public long lLen(final String key) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long len = connection.lLen(serializer.serialize(CACHE_PREFIX + key));
                return len;
            }
        });
        return result;
    }

    @Override
    public long lRem(final String key, final long count, final String value) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long len = connection.lRem(serializer.serialize(CACHE_PREFIX + key),
                        count, serializer.serialize(value));
                return len;
            }
        });
        return result;
    }

    @Override
    public long lPush(final String key, final String value) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.lPush(serializer.serialize(CACHE_PREFIX + key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public long rPush(final String key, final String value) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.rPush(serializer.serialize(CACHE_PREFIX + key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public List<String> lRange(final String key, final long start, final long end) {
        List<String> list = redisTemplate.execute(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                List<byte[]> array = connection.lRange(serializer.serialize(CACHE_PREFIX + key),
                        start, end);
                List<String> result = new ArrayList<>();
                if (array != null) {
                    for (byte[] entry: array) {
                        result.add(serializer.deserialize(entry));
                    }
                }
                return result;
            }
        });
        return list;
    }

    @Override
    public String lPop(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res =  connection.lPop(serializer.serialize(CACHE_PREFIX + key));
                return serializer.deserialize(res);
            }
        });
        return result;
    }

    @Override
    public String rPop(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res =  connection.rPop(serializer.serialize(CACHE_PREFIX + key));
                return serializer.deserialize(res);
            }
        });
        return result;
    }

    @Override
    public Boolean exits(final String key) {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                Boolean res =  connection.exists(serializer.serialize(CACHE_PREFIX + key));
                return res;
            }
        });
        return result;
    }
}
