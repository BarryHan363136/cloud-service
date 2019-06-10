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
import java.util.Map;

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
        File file = new File("C:/Users/qxv0963/Desktop/TempFiles/DFS/005.jpg");
        String[] result = fastDFSClient.uploadFile("group1", file, "jpg");
        log.info("============>"+result.length);
        log.info("============>"+result[0]);
        log.info("============>"+result[1]);
        log.info("============>"+JSON.toJSONString(result));
    }

    /**
     * 测试通过
     * */
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

    /**
     * 获取文件信息,目前测试不通过
     * {"crc32":-296782605,"createTimestamp":1560132737000,"fileSize":478574,"sourceIpAddr":"192.168.33.161"}
     * */
    @Test
    public void testGetFileInfo() {
        String groupName = "group1";
        String remoteFileName = "M00/00/00/wKghoVz9vIGATPsTAAdNbu5PdPM767.jpg";
        FileInfo file = fastDFSClient.getFileInfo(groupName, remoteFileName);
        if (file!=null){
            log.info("==================>"+JSON.toJSONString(file));
        }
    }

    /**
     * 返回值示例:
     * {"bgcolor":"#FFFFFF","author":"Mike","heigth":"600","width":"800"}
     * */
    @Test
    public void testGetFileMetadata() {
        String groupName = "group1";
        String remoteFileName = "M00/00/00/wKghoVz97F-AZhfNAACmJ5BCDew773.jpg";
        Map<String,String> map = fastDFSClient.getFileMetadata(groupName, remoteFileName);
        if (map!=null){
            log.info("==================>"+JSON.toJSONString(map));
        }
    }



}
