package com.barry.cloud.platform.fastdfs.config;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @description: FastDFS文件上传下载工具类
 * @author: Tongshan.Han
 * @time: 2019/5/29 10:39
 * 参考:
 * https://github.com/happyfish100/fastdfs-client-java
 * https://gary0416.iteye.com/blog/1148790
 * https://www.cnblogs.com/eer123/p/9218758.html
 * https://blog.csdn.net/acmman/article/details/80959338
 * https://blog.csdn.net/liweizhong193516/article/details/53244134/
 * http://www.makaidong.com/%E5%8D%9A%E5%AE%A2%E5%9B%AD%E7%83%AD/27295.shtml
 */
@Component
@Slf4j
public class FastDFSClient implements CommandLineRunner {

    private static final String CONFIG_FILENAME = "fdfs_client.conf";
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient storageClient = null;

    @Override
    public void run(String... args) throws Exception {
        log.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        //String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
        ClientGlobal.init(this.getClass().getClassLoader().getResource(CONFIG_FILENAME).getPath());
        trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient = new StorageClient(trackerServer, storageServer);
    }

    /**
     * 上传文件,返回值为String数组,数组中包含两个值,[0]groupName;[1]文件路径及文件名
     * 返回值示例: ["group1","M00/00/00/wKghnlzuLAGAFHsbAAC3RwX2LxM075.jpg"]
     * @param file 文件对象
     * @param fileExtName 文件名
     * @return
     */
    public String[] uploadFile(String groupName, File file, String fileExtName) {
        return uploadFile(groupName, file, fileExtName,null);
    }

    /**
     * 上传文件,返回值为String数组,数组中包含两个值,[0]groupName;[1]文件路径及文件名
     * @param file 文件对象
     * @param fileExtName 文件扩展名
     * @param metaList 文件元数据
     * @return
     */
    public  String[] uploadFile(String groupName, File file, String fileExtName, Map<String,String> metaList) {
        try {
            byte[] buff = IOUtils.toByteArray(new FileInputStream(file));
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<String,String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name,value);
                }
            }
            return storageClient.upload_file(groupName,buff,fileExtName,nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件元数据
     * @param remoteFileName 文件ID
     * @return
     */
    public Map<String,String> getFileMetadata(String groupName,String remoteFileName) {
        try {
            NameValuePair[] metaList = storageClient.get_metadata(groupName, remoteFileName);
            if (metaList != null) {
                HashMap<String,String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(),metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     * @param fileId 文件ID
     * @return 删除失败返回-1，否则返回0
     */
    public int deleteFile(String groupname,String fileId) {
        try {
            return storageClient.delete_file(groupname,fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void downloadFile(String groupName,String remoteFilename, String filePath) {
        try {
            byte[] b = storageClient.download_file(groupName, remoteFilename);
            IOUtils.write(b, new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据byte数组，生成文件
     */
    public void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据groupName和文件名获取文件信息
     * */
    public FileInfo getFileInfo(String groupName, String remoteFileName) {
        try {
            FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
            return fileInfo;
        } catch (IOException e) {
            log.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            log.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

//    public static void main(String[] args) throws Exception {
//        FastDFSClient client = new FastDFSClient();
//        File file = new File("C:/Users/qxv0963/Desktop/TempFiles/test.jpg");
//        String[] result = client.uploadFile(file, "jpg");
//        System.out.println("============>"+result.length);
//        System.out.println("============>"+result[0]);
//        System.out.println("============>"+result[1]);
//        System.out.println("============>"+JSON.toJSONString(result));
//    }


}
