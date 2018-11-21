//package com.hzhc.dyeing;
//
//import com.google.protobuf.InvalidProtocolBufferException;
//import com.hc.sys.core.netty.NettyClient;
//import com.hc.sys.core.netty.ProtoMessage;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelId;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import test.model.protobuf.TestProto;
//@SpringBootTest
//public class NettyClientTest {
//    private Logger logger= LoggerFactory.getLogger(NettyClientTest.class);
//    protected ChannelId m_testClientChannelId = null;
//    @Test
//    public void testNetty() {
//        int port = 3000;
//        //创建客户端
//        NettyClient clt = new NettyClient(){
//            @Override
//            protected void onConnected(Channel channel){
//                logger.info("连接服务器成功");
//            }
//            @Override
//            protected void onDisconnect(Channel channel){
//                logger.warn("已从服务器断开连接");
//            }
//            @Override
//            protected void onReceive(ProtoMessage msg){
//                switch (TestProto.MessageType.forNumber(msg.msgType)) {
//                    case SMSG_ACK:
//                        try {
//                            TestProto.smsg_ack ack = TestProto.smsg_ack.parseFrom(msg.msgValue);
//                            System.out.println(String.format("***Client***ack:%s, result:%d", ack.getAck(), ack.getResult()));
//                        } catch (InvalidProtocolBufferException e) {
//                            e.printStackTrace();
//                        }
//                }
//            }
//        };
//
//        //连接服务端
//        clt.connect("127.0.0.1", port);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //客户端向服务端发送数据
//        TestProto.cmsg_test.Builder cmsg = TestProto.cmsg_test.newBuilder();
//        TestProto.cmsg_test.Builder cmsgs = TestProto.cmsg_test.newBuilder();
//        cmsg.setQuery("tom");
//        cmsgs.setQuery("123456");
//        clt.send(1, cmsg.build());
//        clt.send(2, cmsgs.build());
//
//        try {
//            //主线程休眠1秒钟，保证数据都收发完成
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //客户端主动断开连接，测试ok
//        //clt.close();
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
