package com.barry.cloud.platform.fastdfs.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.csource.common.IniFileReader;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @description: FastDFS Test Codes
 * @author: Tongshan.Han
 * @time: 2019/6/6 16:52
 */
@Slf4j
public class FileUtilsTest {

    @Test
    public void initTest() throws IOException {
        IniFileReader iniFileReader = new IniFileReader(this.getClass().getClassLoader().getResource("fdfs_client.conf").getPath());
        System.out.println("getConfFilename: " + iniFileReader.getConfFilename());
        System.out.println("connect_timeout: " + iniFileReader.getIntValue("connect_timeout", 3));
        System.out.println("network_timeout: " + iniFileReader.getIntValue("network_timeout", 45));
        System.out.println("charset: " + iniFileReader.getStrValue("charset"));
        System.out.println("http.tracker_http_port: " + iniFileReader.getIntValue("http.tracker_http_port", 8080));
        System.out.println("http.anti_steal_token: " + iniFileReader.getBoolValue("http.anti_steal_token", false));
        System.out.println("http.secret_key: " + iniFileReader.getStrValue("http.secret_key"));
        String[] tracker_servers = iniFileReader.getValues("tracker_server");
        if (tracker_servers != null) {
            System.out.println("tracker_servers.length: " + tracker_servers.length);
            for (int i = 0; i < tracker_servers.length; i++) {
                System.out.println(String.format("tracker_servers[%s]: %s", i, tracker_servers[i]));
            }
        }
    }

    @Test
    public void testUploadFile2(){
        try {
            ClientGlobal.init(this.getClass().getClassLoader().getResource("fdfs_client.conf").getPath());
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            NameValuePair[] meta_list = new NameValuePair[4];
            meta_list[0] = new NameValuePair("width", "800");
            meta_list[1] = new NameValuePair("heigth", "600");
            meta_list[2] = new NameValuePair("bgcolor", "#FFFFFF");
            meta_list[3] = new NameValuePair("author", "Mike");

            File file = new File("C:/Users/qxv0963/Desktop/TempFiles/DFS/006.jpg");
            byte[] file_buff = IOUtils.toByteArray(new FileInputStream(file));
            String[] results = storageClient.upload_file(file_buff, "jpg", meta_list);
            log.info("===================>"+JSON.toJSONString(results));
        } catch (IOException e) {
            log.error("testGetFile IOException {} ", e);
        } catch (MyException e) {
            log.error("testGetFile MyException {} ", e);
        }
    }

    @Test
    public void testGetFile2() {
        try {
            ClientGlobal.init(this.getClass().getClassLoader().getResource("fdfs_client.conf").getPath());
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            FileInfo fileInfo = storageClient.get_file_info("group1","M00/00/00/wKghoVz97F-AZhfNAACmJ5BCDew773.jpg");
            if (fileInfo!=null){
                log.info("===================>" + JSON.toJSONString(fileInfo));
            }
        } catch (IOException e) {
            log.error("testGetFile IOException {} ", e);
        } catch (MyException e) {
            log.error("testGetFile MyException {} ", e);
        }
    }


}
