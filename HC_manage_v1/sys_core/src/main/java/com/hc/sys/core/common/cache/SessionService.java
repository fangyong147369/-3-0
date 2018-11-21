package com.hc.sys.core.common.cache;//package com.hc.sys.core.common.cache;
//import com.hc.sys.common.util.idService.SessionId;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//@Service
//public class SessionService {
//    @Resource
//    protected RedisService redisService;
//
//    /**
//     * 删除缓存
//     *
//     * @param key
//     */
//    public void delSession(String key, HttpServletRequest request) {
//        redisService.mapDel(sessionId(request), key);
//    }
//
//    /**
//     * 保存缓存 过期时间默认30s
//     *
//     * @param key
//     * @param val
//     */
//    public String setSession(String key, Object val, long expire, HttpServletRequest request) {
//        String sessionId = sessionId(request);
//        redisService.mapPut(sessionId, key, val);
//        return sessionId;
//    }
//
//    /**
//     * 保存缓存 过期时间默认30s
//     *
//     * @param key
//     * @param val
//     */
//    public String setSession(String key, Object val, HttpServletRequest request) {
//        String sessionId = sessionId(request);
//        redisService.mapPut(sessionId, key, val);
//        return sessionId;
//    }
//
//
//    /**
//     * 获取缓存
//     *
//     * @param key
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public <T> T getSession(String key, Class<T> clazz, HttpServletRequest request) {
//        T t = redisService.mapGet(sessionId(request), key, clazz);
//        return t;
//    }
//
//    private String sessionId(HttpServletRequest request) {
//        Object hid = request.getAttribute(SessionId.COOKIE_SESSIONID);
//        String sid = hid == null ? "" : "SESSION:" + hid.toString();
//        return sid;
//    }
//}
