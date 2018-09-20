package com.barry.cloud.platform.oap.service.imp;

import com.barry.cloud.platform.oap.annotation.NeedSetField;
import com.barry.cloud.platform.oap.entity.Order;
import com.barry.cloud.platform.oap.mapper.OrderMapper;
import com.barry.cloud.platform.oap.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/18 14:05
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @NeedSetField
    public Page<Order> findOrders(Integer pageNum, Integer pageSize) {
        Page<Order> page = PageHelper.startPage(pageNum, pageSize);
        orderMapper.select(null);

        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return page;
    }

}
