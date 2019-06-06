package com.barry.cloud.platform.fastdfs.service;

import com.alibaba.fastjson.JSON;
import com.barry.cloud.platform.fastdfs.base.BaseFastDFSTest;
import com.barry.cloud.platform.fastdfs.config.FastDFSClient;
import com.barry.cloud.platform.fastdfs.entity.FastDFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
     * */
    @Test
    public void testUploadFile(){
        File file = new File("C:/Users/qxv0963/Desktop/TempFiles/FDFS/upload-log.txt");
        String[] result = fastDFSClient.uploadFile(file, "txt");
        log.info("============>"+result.length);
        log.info("============>"+result[0]);
        log.info("============>"+result[1]);
        log.info("============>"+JSON.toJSONString(result));
    }

    @Test
    public void testGetFile(){
        String groupName = "group1";
        String remoteFileName = "M00/00/00/wKghoVz4pJGAa7WYAAOwisOcyZc640_big.jpg";
        FileInfo file = fastDFSClient.getFile(groupName, remoteFileName);
        if (file!=null){
            log.info("==================>"+JSON.toJSONString(file));
        }
    }

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
