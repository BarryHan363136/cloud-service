package com.barry.cloud.platform.security.jwt.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/21 9:55
 */
@Slf4j
public class StreamTest {

    @Test
    public void test(){
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");
        log.info("=====================>"+String.join(",", roles));
        String str = roles.stream().collect(Collectors.joining(",")).toString();
        log.info("=====================>"+str);
    }






}
