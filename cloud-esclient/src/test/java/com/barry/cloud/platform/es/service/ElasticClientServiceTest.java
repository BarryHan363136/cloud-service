package com.barry.cloud.platform.es.service;

import com.barry.cloud.platform.es.base.BaseESTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ElasticClientServiceTest extends BaseESTest {

    private static final String ES_INDEX_NAME = "spark_data_staff";
    private static final String ES_TYPE = "employee";

    @Autowired
    private TransportClient transportClient;

    /**
     * 根据指定ID查询数据
     * */
    @Test
    public void testGetRequest(){
        String id = "203785090214396935";
        GetRequestBuilder response = transportClient.prepareGet(ES_INDEX_NAME, ES_TYPE, id);
        if (response!=null){

        }


    }














}
