package com.barry.cloud.platform.protobuf.utils;

import com.barry.cloud.platform.protobuf.model.GpsData;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @description: protobuf ser testing
 * @author: Tongshan.Han
 * @time: 2019/6/12 16:04
 * 序列化参考: https://www.cnblogs.com/sanshengshui/p/9741655.html
 */
@Slf4j
public class GpsProtobufTest {

    @Test
    public void testProSer() {
        try {
            GpsData.gps_data.Builder gps_builder = GpsData.gps_data.newBuilder();
            gps_builder.setAltitude(1);
            gps_builder.setDataTime("2017-12-17 16:21:44");
            gps_builder.setGpsStatus(1);
            gps_builder.setLat(39.123);
            gps_builder.setLon(120.112);
            gps_builder.setDirection(30.2F);
            gps_builder.setId(100L);

            GpsData.gps_data gps_data = gps_builder.build();
            log.info(gps_data.toString());
            log.info("===== 构建GPS模型结束 =====");
            for (byte b : gps_data.toByteArray()) {
                log.info("" + b);
            }
            System.out.println("\n" + "bytes长度" + gps_data.toByteString().size());
            System.out.println("===== gps Byte 结束 =====");

            System.out.println("===== 使用gps 反序列化生成对象开始 =====");

            GpsData.gps_data gd = GpsData.gps_data.parseFrom(gps_data.toByteArray());

            System.out.print(gd.toString());
            System.out.println("===== 使用gps 反序列化生成对象结束 =====");

            System.out.println("===== 使用gps 转成json对象开始 =====");

            String jsonFormatM = JsonFormat.printer().print(gd);
            System.out.println(jsonFormatM);
            System.out.println("json数据大小：" + jsonFormatM.getBytes().length);
            System.out.println("===== 使用gps 转成json对象结束 =====");
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProSer2() {
        try {
            GpsData.gps_data.Builder gps_builder = GpsData.gps_data.newBuilder();
            gps_builder.setAltitude(1);
            gps_builder.setDataTime("2017-12-17 16:21:44");
            gps_builder.setGpsStatus(1);
            gps_builder.setLat(39.123);
            gps_builder.setLon(120.112);
            gps_builder.setDirection(30.2F);
            gps_builder.setId(100L);

            GpsData.gps_data gps_data = gps_builder.build();
            /** 将数据写到输出流 */
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            gps_data.writeTo(output);
            /** 将数据序列化后发送 */
            byte[] byteArray = output.toByteArray();
            /** 接收到流并读取 */
            ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
            /** 反序列化 */
            GpsData.gps_data gpsData = GpsData.gps_data.parseFrom(input);
            log.info("=====getAltitude=======>:" + gpsData.getAltitude());
            log.info("=====getDataTime=======>:" + gpsData.getDataTime());
            log.info("=====getGpsStatus=======>:" + gpsData.getGpsStatus());
            log.info("=====getLat=======>:" + gpsData.getLat());
            log.info("=====getLon=======>:" + gpsData.getLon());
            log.info("=====getDirection=======>:" + gpsData.getDirection());
            log.info("=====getId=======>:" + gpsData.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
