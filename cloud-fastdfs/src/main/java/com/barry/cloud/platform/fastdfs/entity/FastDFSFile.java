package com.barry.cloud.platform.fastdfs.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * @description: FastDFSFile Entity
 * @author: Tongshan.Han
 * @time: 2019/5/29 17:07
 */
@Data
public class FastDFSFile implements Serializable {

    private static final long serialVersionUID = -5585275011316277931L;

    private String name;
    private byte[] content;
    private String ext;
    private String md5;
    private String author;


    public FastDFSFile(String fileName, byte[] fileBuff, String ext) {
        this.name = fileName;
        this.content = fileBuff;
        this.ext = ext;
    }
}
