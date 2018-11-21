package com.hc.sys.netty.netty;

import com.google.protobuf.MessageLite;
import com.hc.sys.common.util.log.LogUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

//netty客户端，例子见NettyServerTest.java文件

public class NettyClient {
    public NettyClient(){
        m_ip = "127.0.0.1";
        m_port = 3000;
        m_channel = null;
        m_thread = null;
        m_workGroup = null;
    }

    public void     connect(String ip, int port){
        m_ip = ip;
        m_port = port;
        m_thread = new Thread(){
            @Override
            public void run() {
                m_workGroup = new NioEventLoopGroup();
                try {
                    Bootstrap b = new Bootstrap();
                    b.group(m_workGroup).channel(NioSocketChannel.class)
                            .option(ChannelOption.TCP_NODELAY, true)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ChannelPipeline channelPipeline = ch.pipeline();
                                    //读取包长度
                                    channelPipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                    channelPipeline.addLast("decode", new ProtoDecode());
                                    channelPipeline.addLast("encode", new ProtoEncode());

                                    ch.pipeline().addLast(new NettyClientHandle(NettyClient.this));
                                }
                            });

                    //发起异步连接操作
                    ChannelFuture f = b.connect(m_ip, m_port).sync();
                    //等待客户端链路关闭
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
                } finally {
                    m_workGroup.shutdownGracefully();
                }
            }
        };
        m_thread.start();
    }

    public void     close(){
        try {
            if (m_workGroup != null)
                m_workGroup.shutdownGracefully().sync();
            m_workGroup = null;
            m_thread = null;
        } catch (InterruptedException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    public boolean  send(int msgType, MessageLite msg){
        ProtoMessage proto = new ProtoMessage();
        proto.msgType = msgType;
        proto.msgValue = msg.toByteArray();
        m_channel.writeAndFlush(proto);
        return true;
    }

    //接收服务端消息，注意，socket线程调用的这个函数
    protected void onReceive(ProtoMessage msg){
        //需要覆盖
    }
    //客户端和服务端建立连接，注意，socket线程调用的这个函数
    protected void onConnected(Channel channel){
        //需要覆盖
    }
    //客户端和服务端断开连接，注意，socket线程调用的这个函数
    protected void onDisconnect(Channel channel){
        //需要覆盖
    }

    final protected void setChannel(Channel channel){
        m_channel = channel;
    }

    protected String    m_ip;
    protected int       m_port;
    protected Channel m_channel;
    protected Thread    m_thread;
    protected EventLoopGroup m_workGroup;
}
