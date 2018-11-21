package com.hc.sys.netty.thread;

import com.hc.sys.common.util.log.LogUtil;
import io.netty.channel.ChannelId;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventThread {
    public static class Event {
        public Event() {}

        public int  id = 0;
        public ChannelId channelId = null;
        public Object obj = null;
    }

    public EventThread(){

    }

    public void create(){
        create(50);
    }

    public void create(int interval){
        m_interval = interval;
        m_thread = new Thread() {
            @Override
            public void run() {
                onInitThread();
                while (m_bRun) {
                    long begTime = System.currentTimeMillis();
                    ArrayList<Event> lstEvent = new ArrayList<>();
                    m_lock.lock();
                    lstEvent.addAll(m_lstEvent);
                    m_lstEvent.clear();
                    m_lock.unlock();

                    for (int i = 0; i < lstEvent.size(); ++i) {
                        Event evt = lstEvent.get(i);
                        handleEvent(evt);
                    }
                    long endTime = System.currentTimeMillis();
                    try {
                        if (endTime - begTime < m_interval)
                            sleep(m_interval - (endTime - begTime));
                    } catch (InterruptedException e) {
                        LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
                    }
                }
                onUninitThread();
            }
        };
    }

    public void start(){
        if (m_thread == null)
            return;

        m_bRun = true;
        m_thread.start();
    }

    public void join(){
        if (m_thread == null)
            return;
        try {
            m_thread.join();
        } catch (InterruptedException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    public void stop() {
        if (m_thread == null)
            return;

        m_bRun = false;
    }

    public void pushEvent(Event evt) {
        m_lock.lock();
        m_lstEvent.add(evt);
        m_lock.unlock();
    }

    protected void onInitThread(){

    }

    protected void onUninitThread(){}
    //重载这个函数，处理事件
    protected void handleEvent(Event evt) {

    }

    protected long      m_interval = 50;
    protected boolean   m_bRun = false;
    protected Thread    m_thread = null;
    protected Lock      m_lock = new ReentrantLock();
    protected ArrayList<Event> m_lstEvent = new ArrayList<>();
}
