package com.hc.sys.netty.netty;

public class ProtoMessage {
    public int      msgType;
    public long     resData;    //需要应答给客户端的数据，默认是0
    public byte[]   msgValue;
}
