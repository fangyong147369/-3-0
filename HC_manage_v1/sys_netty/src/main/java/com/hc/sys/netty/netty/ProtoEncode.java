package com.hc.sys.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ProtoEncode extends MessageToByteEncoder<ProtoMessage> {
    @Override
    protected void encode(ChannelHandlerContext cc, ProtoMessage msg, ByteBuf out) throws Exception {
        int length = 12 + msg.msgValue.length;
        out.writeInt(length);
        out.writeInt(msg.msgType);
        out.writeLong(msg.resData);
        out.writeBytes(msg.msgValue);
    }
}
