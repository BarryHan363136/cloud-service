package com.barry.cloud.platform.es.service;

import com.barry.cloud.platform.es.base.BaseESTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: hts
 * @Date: 2020/10/24 19:02
 * @Description:
 */
@Slf4j
public class ElasticServiceTest extends BaseESTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testQueryDeplomentInfo(){
        String url= "http://10.154.11.140:12400/api/v1/deployments/85d08e5221184f81a27b03e14d040456";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "ApiKey cjZnR3RYUUJqLTh2eU5tX1ZYQ0g6aGdpRzlmeEhRNHVQN3lSNnR6LVNuUQ==");
        RestTemplate template = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String sttr = response.getBody();
        log.info("sttr="+sttr);

    }


}
