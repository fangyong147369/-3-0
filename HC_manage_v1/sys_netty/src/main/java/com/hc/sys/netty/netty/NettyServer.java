package com.hc.sys.netty.netty;

import com.google.protobuf.MessageLite;
import com.hc.sys.common.util.log.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

//netty服务端，例子见NettyServerTest.java文件
@Component
public class NettyServer {
    public NettyServer() {
    }

    public boolean create(int portport) {
        if (portport > 0) {
            m_port = portport;
            m_thread = new Thread() {
                @Override
                public void run() {
                    m_bossGroup = new NioEventLoopGroup();
                    m_workGroup = new NioEventLoopGroup();
                    try {
                        // NIO服务器端的辅助启动类 降低服务器开发难度
                        ServerBootstrap serverBootstrap = new ServerBootstrap();
                        serverBootstrap.group(m_bossGroup, m_workGroup)
                                .channel(NioServerSocketChannel.class)// 类似NIO中serverSocketChannel
                                .option(ChannelOption.SO_BACKLOG, 1024)// 配置TCP参数
                                .option(ChannelOption.SO_BACKLOG, 1024) // 设置tcp缓冲区
                                .option(ChannelOption.SO_SNDBUF, 32 * 1024) // 设置发送缓冲大小
                                .option(ChannelOption.SO_RCVBUF, 32 * 1024) // 这是接收缓冲大小
                                .option(ChannelOption.SO_KEEPALIVE, true) // 保持连接
                                .childHandler(new NettyServerChannelInitializer(NettyServer.this));// 最后绑定I/O事件的处理类
                        // 处理网络IO事件 			// 服务器启动后 绑定监听端口 同步等待成功 主要用于异步操作的通知回调 回调处理用的ChildChannelHandler

                        ChannelFuture f = serverBootstrap.bind(m_port).sync();
                        LogUtil.info("---netty创建成功----端口号为:  " + portport);
                        f.channel().closeFuture().sync();
                    } catch (InterruptedException e) {
                        LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
                    } finally {
                        // 优雅退出 释放线程池资源
                        m_bossGroup.shutdownGracefully();
                        m_workGroup.shutdownGracefully();
                    }
                    m_bossGroup = null;
                    m_workGroup = null;
                }
            };
            return true;
        } else {
            LogUtil.warn("---nettyServer创建失败----");
            return false;
        }

    }

    public void start() {
        if (m_thread.isAlive())
            return;
        LogUtil.info("---nettyServer开始服务---");
        m_thread.start();
    }

    //关闭服务端socket
    public void close() {
        try {
            if (m_bossGroup != null)
                m_bossGroup.shutdownGracefully().sync();
            if (m_workGroup != null)
                m_workGroup.shutdownGracefully().sync();
        } catch (InterruptedException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    //关闭指定客户端连接
    public void close(ChannelId id) {
        Channel channel = m_channelGroup.find(id);
        if (channel == null)
            return;

        channel.close();
    }

    public ChannelGroup getChannelGroup() {
        return m_channelGroup;
    }

    //发送消息给客户端
    public void send(ChannelId id, int msgType, MessageLite msg) {
        send(id, msgType, 0, msg);
    }

    //发送消息给客户端
    public void send(ChannelId id, int msgType, long resData, MessageLite msg) {
        Channel channel = m_channelGroup.find(id);
        if (channel == null)
            return;
        ProtoMessage proto = new ProtoMessage();
        proto.msgType = msgType;
        proto.resData = resData;
        proto.msgValue = msg.toByteArray();
        channel.writeAndFlush(proto);
    }

    //接收客户端消息，注意，可能有多个线程调用这个函数
    protected void onReceive(ChannelId channelId, ProtoMessage msg) {
        //需要覆盖
    }

    //客户端和服务端建立连接，注意，可能有多个线程调用这个函数
    protected void onConnected(Channel channel) {
        //需要覆盖
    }

    //客户端和服务端断开连接，注意，可能有多个线程调用这个函数
    protected void onDisconnected(Channel channel) {
        //需要覆盖
    }

    protected int m_port = 0;
    protected Thread m_thread = null;
    protected ChannelGroup m_channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    protected EventLoopGroup m_bossGroup = null;
    protected EventLoopGroup m_workGroup = null;
}
