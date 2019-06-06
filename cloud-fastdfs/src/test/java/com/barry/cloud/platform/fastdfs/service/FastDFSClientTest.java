package com.barry.cloud.platform.fastdfs.service;

import com.alibaba.fastjson.JSON;
import com.barry.cloud.platform.fastdfs.base.BaseFastDFSTest;
import com.barry.cloud.platform.fastdfs.config.FastDFSClient;
import com.barry.cloud.platform.fastdfs.entity.FastDFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * @description: FastDFSClient Function Testing
 * @author: Tongshan.Han
 * @time: 2019/5/29 10:54
 */
@Slf4j
public class FastDFSClientTest extends BaseFastDFSTest {

    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * ["group1","M00/00/00/wKghnlzuLAGAFHsbAAC3RwX2LxM075.jpg"]
     * ["group1","M00/00/00/wKghoVz4yziAIMwQAAHSF9r4APQ683.jpg"]
     * */
    @Test
    public void testUploadFile(){
        File file = new File("C:/Users/qxv0963/Desktop/TempFiles/DFS/003.jpg");
        String[] result = fastDFSClient.uploadFile(file, "jpg");
        log.info("============>"+result.length);
        log.info("============>"+result[0]);
        log.info("============>"+result[1]);
        log.info("============>"+JSON.toJSONString(result));
        if (result!=null && result.length==2){
            FileInfo fileInfo = fastDFSClient.getFile(result[0], result[1]);
            if (file!=null){
                log.info("==================>"+JSON.toJSONString(file));
                System.out.println("CRC32签名：" + fileInfo.getCrc32());
                System.out.println("文件大小：" + fileInfo.getFileSize());
                System.out.println("服务器地址：" + fileInfo.getSourceIpAddr());
                System.out.println("创建时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fileInfo.getCreateTimestamp()));
            }

        }
    }

    @Test
    public void testGetFile() {
        String groupName = "group1";
        String remoteFileName = "M00/00/00/wKghoVz4yziAIMwQAAHSF9r4APQ683.jpg";
        FileInfo file = fastDFSClient.getFile(groupName, remoteFileName);
        if (file!=null){
            log.info("==================>"+JSON.toJSONString(file));
        }
    }

//    @Test
//    public void testGetFile2(){
//        try {
//            ClientGlobal.init(this.getClass().getClassLoader().getResource("fastdfs-client.properties").getPath());
//            TrackerClient tracker = new TrackerClient();
//            TrackerServer trackerServer = tracker.getConnection();
//            StorageServer storageServer = null;
//            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//            //FileInfo fileInfo = storageClient.get_file_info("group1", "M00/00/00/wKghoVz4yziAIMwQAAHSF9r4APQ683.jpg");
//            NameValuePair[] get_metadata = storageClient.get_metadata("group1", "M00/00/00/wKghoVz4yziAIMwQAAHSF9r4APQ683.jpg");
//            for (NameValuePair nameValuePair: get_metadata) {
//                log.info("==================>name: " + nameValuePair.getName() + " value: " + nameValuePair.getValue());
//            }
//        } catch (IOException e) {
//            log.error("testGetFile IOException {} ", e);
//        } catch (MyException e) {
//            log.error("testGetFile MyException {} ", e);
//        }
//    }

    @Test
    public void testDownload() {
        String filePath = "C:/Users/qxv0963/Desktop/TempFiles/DFS/1000.jpg";
        fastDFSClient.downloadFile("group1", "M00/00/00/wKghoVz4pJGAa7WYAAOwisOcyZc640.jpg" , filePath);
        String path = filePath;
        File file = new File(path);
        if (file!=null){
            log.info("==================>"+file.getPath());
        }
    }


}
