package com.barry.cloud.platform.fastdfs.service;

import com.alibaba.fastjson.JSON;
import com.barry.cloud.platform.fastdfs.base.BaseFastDFSTest;
import com.barry.cloud.platform.fastdfs.config.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;

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
        String[] result = fastDFSClient.uploadFile(file, "jpg");
        log.info("============>"+result.length);
        log.info("============>"+result[0]);
        log.info("============>"+result[1]);
        log.info("============>"+JSON.toJSONString(result));
    }


}
