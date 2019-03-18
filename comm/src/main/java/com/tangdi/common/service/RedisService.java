package com.tangdi.common.service;

import java.util.List;
import java.util.Map;

public interface RedisService {
    boolean setByCompress(final String key, final long seconds, final String value);

    String getByDecompress(final String key);

    boolean set(final String key, final long seconds, final String value);

    boolean set(final String key, final String value);

    long incr(final String key);

    long incrBy(final String key, final long step);

    long decr(final String key);

    long decrBy(final String key, final long step);

    String get(final String key);

    long del(final String key);

    boolean hSet(final String key, final String field, final String value);

    boolean hMSet(final String key, Map<String, String> map);

    String hGet(final String key, final String field);

    long hDel(final String key, final String field);

    long hLen(final String key);

    Map<String, String> hGetAll(final String key);

    boolean expire(final String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> boolean setList(String key, final long seconds, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    long lLen(final String key);

    long lRem(String key, long count, String value);

    long lPush(final String key, String value);

    long rPush(final String key, String value);

    List<String> lRange(final String key, long start, long end);

    String lPop(final String key);

    String rPop(final String key);

    Boolean exits(final String key);
}
