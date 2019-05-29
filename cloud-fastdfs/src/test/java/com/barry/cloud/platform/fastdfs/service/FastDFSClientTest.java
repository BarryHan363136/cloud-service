package com.barry.cloud.platform.fastdfs.service;

import com.alibaba.fastjson.JSON;
import com.barry.cloud.platform.fastdfs.base.BaseFastDFSTest;
import com.barry.cloud.platform.fastdfs.config.FastDFSClient;
import com.barry.cloud.platform.fastdfs.entity.FastDFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.FileInfo;
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

    /**
     * ["group1","M00/00/00/wKghnlzuVk-AVAjoAAAAMHsxggo76.jfif"]
     * */
    @Test
    public void testUploadFile2() throws IOException {
        File file = new File("C:/Users/qxv0963/Desktop/TempFiles/FDFS/003.jfif");
        String fileName = "003.jfif";

        byte[] fileBuff = null;
        InputStream inputStream = IOUtils.toInputStream("C:/Users/qxv0963/Desktop/TempFiles/FDFS/003.jfif", "UTF-8");
        if(inputStream!=null){
            int len1 = inputStream.available();
            fileBuff = new byte[len1];
            inputStream.read(fileBuff);
        }
        inputStream.close();
        FastDFSFile fastDFSFile = new FastDFSFile(fileName, fileBuff, "jfif");
        String[] result = fastDFSClient.upload(fastDFSFile);
        log.info("============>"+result.length);
        log.info("============>"+result[0]);
        log.info("============>"+result[1]);
        log.info("============>"+JSON.toJSONString(result));
    }

    @Test
    public void testGetFile(){
        String groupName = "group1";
        String remoteFileName = "M00/00/00/wKghnlzuVk-AVAjoAAAAMHsxggo76.jfif";
        FileInfo file = fastDFSClient.getFile(groupName, remoteFileName);
        if (file!=null){
            log.info("==================>"+JSON.toJSONString(file));
        }
    }



}
