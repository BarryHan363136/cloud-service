package com.barry.cloud.platform.serialnumber.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/20 10:50
 */
@Slf4j
public class DateTimeUtilsTest {

    @Test
    public void testTimeToLong() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = "2017-01-01";
        Date date = dateFormat.parse(str);
        log.info(date.getTime()+"");
    }

}
