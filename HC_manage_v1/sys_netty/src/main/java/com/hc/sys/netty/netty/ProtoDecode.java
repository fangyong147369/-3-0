package com.hc.sys.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ProtoDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext paramChannelHandlerContext, ByteBuf in, List<Object> paramList) throws Exception {
        ProtoMessage msg = new ProtoMessage();
        msg.msgType = in.readInt();
        msg.resData = in.readLong();
        msg.msgValue = new byte[in.readableBytes()];
        in.readBytes(msg.msgValue);
        paramList.add(msg);
    }
}
