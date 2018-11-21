//package com.hc.sys.core.common.cache;//package com.hc.sys.core.common.cache;
//import com.alibaba.fastjson.JSON;
//import com.hc.sys.common.util.json.JsonUtil;
//import com.hc.sys.common.util.validate.StringUtil;
//import com.hc.sys.core.common.json.JsonUtils;
//import com.xiaoleilu.hutool.json.JSONUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
///**
// * Created by DFx-Dellbook on 2017/8/8
// */
//@Service
//public class RedisService {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    /**
//     * 保存
//     *
//     * @param key
//     * @param val
//     */
//    public void put(String key, Object val) {
//        if (val != null) {
//            stringRedisTemplate.opsForValue().set(key, formatPutStr(val));
//        }
//    }
//
//    /**
//     * 保存
//     *
//     * @param key
//     * @param val
//     * @param expire 过期时间 秒
//     */
//    public void put(String key, Object val, long expire) {
//        if (val != null) {
//            stringRedisTemplate.opsForValue().set(key, formatPutStr(val), expire, TimeUnit.SECONDS);
//        }
//    }
//
//    /**
//     * 获取
//     *
//     * @param key
//     * @return
//     */
//    public String get(String key) {
//        return get(key, String.class);
//    }
//
//    /**
//     * 根据前缀获取所有前缀是key  的keys
//     *
//     * @param key 前缀
//     * @return
//     */
//    public Set<String> getKeys(String key) {
//        if (StringUtil.isBlank(key)) return null;
//        return stringRedisTemplate.keys(key + "*");
//    }
//
//    /**
//     * 获取对象
//     *
//     * @param key
//     * @param clazz 对象类型
//     * @param <T>   对象类型
//     * @return
//     */
//    public <T> T get(String key, Class<T> clazz) {
//        String val = stringRedisTemplate.opsForValue().get(key);
//        return formatResultBean(val, clazz);
//    }
//
//    /**
//     * 删除值
//     *
//     * @param key
//     * @return
//     */
//    public void del(String key) {
//        stringRedisTemplate.delete(key);
//    }
//
//    /**
//     * 获取list
//     *
//     * @param key
//     * @param clazz
//     * @param <T>   list内对象
//     * @return
//     */
//    public <T> List<T> getList(String key, Class<T> clazz) {
//        String val = stringRedisTemplate.opsForValue().get(key);
//        if (StringUtil.isEmpty(val)) {
//            return null;
//        }
//        return JSONUtil.parseArray(val).toList(clazz);
//    }
//
//    /**
//     * 存放map
//     *
//     * @param key    map集合key
//     * @param mapKey 集合内部key
//     * @param mapVal 值
//     */
//    public void mapPut(String key, String mapKey, Object mapVal) {
//        if (StringUtil.isBlank(key) || StringUtil.isBlank(mapKey) || mapVal == null) return;
//        stringRedisTemplate.opsForHash().put(key, mapKey, formatPutStr(mapVal));
//    }
//
//    /**
//     * 获取map
//     *
//     * @param key map集合key
//     */
//    public Map map(String key) {
//        if (StringUtil.isBlank(key)) return null;
//        return stringRedisTemplate.opsForHash().entries(key);
//    }
//
//
//    /**
//     * 删除map
//     *
//     * @param key    map集合key
//     * @param mapKey 集合内部key
//     */
//    public void mapDel(String key, String mapKey) {
//        if (StringUtil.isBlank(key) || StringUtil.isBlank(mapKey)) return;
//        stringRedisTemplate.opsForHash().delete(key, mapKey);
//    }
//
//
//    /**
//     * 删除map
//     *
//     * @param key    map集合key
//     * @param mapKey 集合内部key
//     */
//    public boolean mapHas(String key, String mapKey) {
//        if (StringUtil.isBlank(key) || StringUtil.isBlank(mapKey)) return false;
//        return stringRedisTemplate.opsForHash().hasKey(key, mapKey);
//    }
//
//    /**
//     * 获取map内val
//     *
//     * @param key    外部key
//     * @param mapKey 集合内部key
//     * @return
//     */
//    public String mapGet(String key, String mapKey) {
//        return mapGet(key, mapKey, String.class);
//    }
//
//
//    /**
//     * 获取map内val
//     *
//     * @param key    外部key
//     * @param mapKey 集合内部key
//     * @param clazz  值类型
//     * @param <T>
//     * @return
//     */
//    public <T> T mapGet(String key, String mapKey, Class<T> clazz) {
//        if (StringUtil.isBlank(key) || StringUtil.isBlank(mapKey)) return null;
//        return formatResultBean(stringRedisTemplate.opsForHash().get(key, mapKey), clazz);
//    }
//
//    /**
//     * 获得map对象所有key
//     *
//     * @param key
//     * @return
//     */
//    public Set<String> mapKeys(String key) {
//        if (StringUtil.isBlank(key)) return null;
//        return stringRedisTemplate.opsForHash().keys(key).stream().map(Object::toString).collect(Collectors.toSet());
//    }
//
//    /**
//     * 更新过期时间
//     *
//     * @param key
//     * @param expire 单位(秒)
//     */
//    public void expire(String key, long expire) {
//        stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
//    }
//
//    /**
//     * 更新过期时间为30分钟
//     *
//     * @param key
//     */
//    public void expire(String key) {
//        expire(key, 30 * 60);
//    }
//
//    /**
//     * 读取队列
//     *
//     * @param name
//     * @return
//     */
//    public String pop(String name) {
//        return listLeftPop(name, String.class);
//    }
//
//    /**
//     * 读取队列
//     *
//     * @param name
//     * @return
//     */
//    public <T> T pop(String name, Class<T> clazz) {
//        return listLeftPop(name, clazz);
//    }
//
//    /**
//     * 写入队列
//     *
//     * @param name  队列名称
//     * @param value 值
//     * @return 队列长度
//     */
//    public long push(String name, Object value) {
//        return listRightPush(name, value);
//    }
//
//    /**
//     * 判断key是否存在
//     *
//     * @param key
//     * @return
//     */
//    public boolean hasKey(String key) {
//        return stringRedisTemplate.hasKey(key);
//    }
//
//    /**
//     * 向对应list尾部推送val
//     *
//     * @param key
//     * @param val
//     * @return
//     */
//    private long listRightPush(String key, Object val) {
//        if (StringUtil.isBlank(key)) return 0;
//        if (val == null) {
//            return stringRedisTemplate.opsForList().size(key);
//        }
//        return stringRedisTemplate.opsForList().rightPush(key, formatPutStr(val));
//    }
//
//    /**
//     * 从对应list头部获取val并删除
//     *
//     * @param key
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    private <T> T listLeftPop(String key, Class<T> clazz) {
//        Object val = stringRedisTemplate.opsForList().leftPop(key);
//        return formatResultBean(val, clazz);
//    }
//
//    /**
//     * 统一json转对象入口
//     *
//     * @param val
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    private static <T> T formatResultBean(Object val, Class<T> clazz) {
//        if (val == null) return null;
//        if (clazz == String.class) return (T) val;
//        return JSON.parseObject(val.toString(), clazz);
//    }
//
//    /**
//     * 存入字符串
//     *
//     * @param val
//     * @return
//     */
//    private static String formatPutStr(Object val) {
//        if (val == null) return null;
//        if (val instanceof String) return val.toString();
//        if (val instanceof Number) return val.toString();
//        return  JsonUtils.toJson(val);
//    }
//}
