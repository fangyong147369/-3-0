package com.hc.sys.netty.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    public NettyServerChannelInitializer(NettyServer server){
        m_nettyServer = server;
    }

    public NettyServer getNettyServer() {
        return m_nettyServer;
    }

    @Override protected void initChannel(SocketChannel ch) throws Exception{
        ChannelPipeline channelPipeline = ch.pipeline();
        //读取包长度
        channelPipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        channelPipeline.addLast("decode", new ProtoDecode());
        channelPipeline.addLast("encode", new ProtoEncode());
        channelPipeline.addLast(new NettyServerHandle(m_nettyServer));
    }

    protected NettyServer   m_nettyServer;
}
