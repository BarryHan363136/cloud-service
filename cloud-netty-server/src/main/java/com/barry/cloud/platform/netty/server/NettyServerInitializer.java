package com.barry.cloud.platform.netty.server;

import com.barry.cloud.platform.netty.model.GpsData;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

/**
 * @description: 设置相应的过滤条件如心跳超时设置，传输协议设置，以及相应的业务实现类
 * @author: Tongshan.Han
 * @time: 2019/6/13 16:59
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline ph = ch.pipeline();

        /** 入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式 */
        ph.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
        /** 解码和编码，应和客户端一致 */
        /** 传输的协议 Protobuf */
        ph.addLast(new ProtobufVarint32FrameDecoder());
        ph.addLast(new ProtobufDecoder(GpsData.gps_data.getDefaultInstance()));
        ph.addLast(new ProtobufVarint32LengthFieldPrepender());
        ph.addLast(new ProtobufEncoder());

        /** 业务逻辑实现类 */
        ph.addLast("nettyServerHandler", new NettyServerHandler());
    }

}