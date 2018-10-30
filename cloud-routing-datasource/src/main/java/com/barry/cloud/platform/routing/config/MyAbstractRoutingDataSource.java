package com.barry.cloud.platform.routing.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author Tongshan.Han@partner.bmw.com
 * @Date 2018/10/30 17:49
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {

    private final int dataSourceNumber;

    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        if (typeKey.equals(DataSourceType.write.getType()))
            return DataSourceType.write.getType();
        // 读 简单负载均衡
        int number = count.getAndAdd(1);
        int lookupKey = number % dataSourceNumber;
        Integer i = lookupKey;
        return i;
    }

}