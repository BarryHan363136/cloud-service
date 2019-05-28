package com.barry.cloud.platform.es.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @Document(indexName = "book",type = "book" , shards = 1, replicas = 0,  refreshInterval = "-1")
 * */
@Data
@Document(indexName="spark_data_articles",type="article",shards=5,replicas=0,indexStoreType="fs",refreshInterval="-1")
public class Article implements Serializable {

    private static final long serialVersionUID = 7784137396396161121L;

    @Id
    private String id;

    private String url;

    private String content;

    private String author;

    //@Field(format=DateFormat.date_time,index=FieldIndex.not_analyzed,store=true,type= FieldType.Object)
    private Date createDate;

    private String remark;

    private String review;

    private Integer serNo;


}
