package com.hc.sys.core.common.test;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/16 13:51
 * @Version: 1.0.0.0
 */
public class SynWindow implements Runnable{
    Object object;
    Object object2;
    String name;
    static int count=10;
    public SynWindow(String name,Object object,Object object2) {
        // TODO Auto-generated constructor stub
        this.object = object;
        this.object2 = object2;
        this.name = name;
    }
    public synchronized void serviceWindow() {

        while(count>0){
            synchronized (object) {
                synchronized (object2) {
                    System.out.println(getName()+"服务"+count+"号客户");
                    count-=1;
                    object2.notify();
                }
                try {
                    object.wait();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        serviceWindow();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        SynWindow s1 = new SynWindow("窗口一", a, b);
        SynWindow s2 = new SynWindow("窗口二", b, c);
        SynWindow s3 = new SynWindow("窗口三", c, a);
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);
        Thread t3 = new Thread(s3);
        t1.start();
        try {
            t1.sleep(100);
        } catch (Exception e) {
            // TODO: handle exception
        }
        t2.start();
        try {
            t2.sleep(100);
        } catch (Exception e) {
            // TODO: handle exception
        }
        t3.start();
    }
}
