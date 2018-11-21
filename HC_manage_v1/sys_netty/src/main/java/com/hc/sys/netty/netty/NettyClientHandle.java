package com.hc.sys.netty.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

public class NettyClientHandle extends SimpleChannelInboundHandler<ProtoMessage> {
    public NettyClientHandle(NettyClient clt){
        m_nettyClient = clt;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMessage msg) throws Exception {
        try {
            m_nettyClient.onReceive(msg);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        m_nettyClient.setChannel(channel);
        m_nettyClient.onConnected(channel);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        m_nettyClient.setChannel(null);
        m_nettyClient.onDisconnect(ctx.channel());
        super.channelInactive(ctx);
    }

    protected NettyClient   m_nettyClient;
}
