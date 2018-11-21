package com.hc.sys.netty.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

public class NettyServerHandle extends SimpleChannelInboundHandler<ProtoMessage> {
    public NettyServerHandle(NettyServer server){
        m_nettyServer = server;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        m_nettyServer.getChannelGroup().add(ctx.channel());
        m_nettyServer.onConnected(ctx.channel());
        super.channelActive(ctx);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        m_nettyServer.getChannelGroup().remove(ctx.channel());
        m_nettyServer.onDisconnected(ctx.channel());
        super.channelInactive(ctx);
    }
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ProtoMessage msg){
        try {
            m_nettyServer.onReceive(ctx.channel().id(), msg);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        //causLogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        ctx.close();
    }

    protected NettyServer  m_nettyServer;
}
