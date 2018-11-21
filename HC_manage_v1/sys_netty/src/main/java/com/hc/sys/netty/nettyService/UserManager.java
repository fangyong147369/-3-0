package com.hc.sys.netty.nettyService;

import io.netty.channel.ChannelId;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private UserManager() {

    }

    public static UserManager threadInstance() {
        UserManager mng = s_userManager.get();
        if (mng == null) {
            mng = new UserManager();
            s_userManager.set(mng);
        }
        return mng;
    }

    public void create() {

    }

    public void destroy() {

    }

    public User find(ChannelId id) {
        User user = m_mapUser.get(id);
        return user;
    }

    public User find(int id) {
        User user = m_mapUserById.get(id);
        return user;
    }

    public void add(ChannelId id, User user) {
        m_mapUser.put(id, user);
        m_mapUserById.put(user.getId(), user);
    }

    public void remove(ChannelId id) {
        User user = m_mapUser.get(id);
        if (user == null)
            return;
        m_mapUser.remove(id);
        m_mapUserById.remove(user.getId());
    }

    protected Map<ChannelId, User> m_mapUser = new HashMap<>();
    protected Map<Integer, User> m_mapUserById = new HashMap<>();

    private static ThreadLocal<UserManager> s_userManager = new ThreadLocal<UserManager>();
}
