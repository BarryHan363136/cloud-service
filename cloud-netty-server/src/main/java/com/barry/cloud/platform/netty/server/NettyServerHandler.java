package com.barry.cloud.platform.netty.server;

import com.barry.cloud.platform.netty.model.GpsData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 业务代码, 继承ChannelInboundHandlerAdapter，手动进行释放，防止数据未处理完就自动释放
 * @author: Tongshan.Han
 * @time: 2019/6/13 17:06
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /** 空闲次数 */
    private AtomicInteger idle_count = new AtomicInteger(1);

    /** 发送次数 */
    private AtomicInteger count = new AtomicInteger(1);

    /**
     * 建立连接时，发送一条消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接的客户端地址:" + ctx.channel().remoteAddress());
        GpsData.gps_data gpsData = GpsData.gps_data.newBuilder().setId(1000L).setAltitude(2000).setDataTime("2019-06-13 16:21:44").setState(0).build();
        ctx.writeAndFlush(gpsData);
        super.channelActive(ctx);
    }

    /**
     * 超时处理 如果5秒没有接受客户端的心跳，就触发; 如果超过两次，则直接关闭;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            /** 如果读通道处于空闲状态，说明没有接收到心跳命令 */
            if (IdleState.READER_IDLE.equals(event.state())) {
                log.info("已经5秒没有接收到客户端的信息了");
                if (idle_count.get() > 1) {
                    log.info("关闭这个不活跃的channel");
                    ctx.channel().close();
                }
                idle_count.getAndIncrement();
            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("第" + count.get() + "次" + ",服务端接受的消息:" + msg);
        try {
            /** 如果是protobuf类型的数据 */
            if (msg instanceof GpsData.gps_data) {
                GpsData.gps_data gpsData = (GpsData.gps_data) msg;
                if (gpsData.getState() == 1) {
                    log.info("客户端业务处理成功!");
                } else if(gpsData.getState() == 2){
                    log.info("接受到客户端发送的心跳!");
                }else{
                    log.info("未知命令!");
                }
            } else {
                log.info("未知数据!" + msg);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
        count.getAndIncrement();
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

